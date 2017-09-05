package com.hy.gcar.utils.http;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.MissingResourceException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hy.common.utils.Global;

/**
 * HTTP Client 工具类
 */
@SuppressWarnings("deprecation")
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);


    public static final int SOCKET_TIMEOUT = 180000;

    public static final int CONNECT_TIMEOUT = 10000;

    public static final int CONNECTION_REQUEST_TIMEOUT = 60000;

    public static final int RETRY_COUNT = 5;

    public static final int MAX_HEADER_COUNT = 200;

    public static final int MAX_LINE_LENGTH = 2000;

    public static final int MAX_TOTAL = 2000;

    public static final int DEFAULT_MAX_PER_ROUTE = 2000;

    /**
     * 默认的HTTP响应实体编码 = "UTF-8"
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    public static final String HTTP_PROXY_URL = "http.proxy.url";

    public static final String HTTP_PROXY_PORT = "http.proxy.port";


    public HttpClientUtil() {
    }

    private static CloseableHttpClient httpClient = null;

    private static HttpClient httpsClient = null;

    private static PoolingHttpClientConnectionManager connManager = null;
    static {
        try {
            // Get properties
            String proxyURL = null;
            String proxyPort = null;
            try {
                proxyURL = Global.getConfig(HTTP_PROXY_URL);
                proxyPort = Global.getConfig(HTTP_PROXY_PORT);
            } catch (MissingResourceException e) {
                logger.error("MissingResourceException", e);
            }

            SSLContext sslContext = SSLContexts.custom().useTLS().build();
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(
                        X509Certificate[] certs, String authType
                ) {
                }

                public void checkServerTrusted(
                        X509Certificate[] certs, String authType
                ) {
                }
            }}, null);

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext))
                    .build();

            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

            HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {

                public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                    if (executionCount >= RETRY_COUNT) {
                        logger.warn("=====httpClient has retry 5 times====");
                        // 如果已经重试了5次，就放弃
                        return false;
                    }
                    if (exception instanceof InterruptedIOException) {
                        // 超时
                        logger.warn("=====httpClient InterruptedIOException====");
                        return false;
                    }
                    if (exception instanceof UnknownHostException) {
                        // 目标服务器不可达
                        logger.warn("=====httpClient UnknownHostException====");
                        return false;
                    }
                    if (exception instanceof SSLException) {
                        // ssl握手异常
                        logger.warn("=====httpClient SSLException====");
                        return false;
                    }
                    HttpClientContext clientContext = HttpClientContext.adapt(context);
                    HttpRequest request = clientContext.getRequest();
                    boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                    if (idempotent) {
                        // 如果请求是幂等的，就再次尝试
                        if (logger.isInfoEnabled())
                            logger.info("Request [" + request.getRequestLine() + "] is idempotent, retry ...");
                        return true;
                    }
                    return false;
                }

            };

            ConnectionKeepAliveStrategy keepAliveStrategy = new ConnectionKeepAliveStrategy() {
                public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                    // Honor 'keep-alive' header
                    HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                    while (it.hasNext()) {
                        HeaderElement he = it.nextElement();
                        String param = he.getName();
                        String value = he.getValue();
                        if (value != null && param.equalsIgnoreCase("timeout")) {
                            try {
                                return Long.parseLong(value) * 1000;
                            } catch (NumberFormatException ignore) {
                            }
                        }
                    }
                    // otherwise keep alive for 60 seconds
                    return 60 * 1000;
                }

            };

            RedirectStrategy redirectStrategy = new MyRedirectStrategy();

            // Create socket configuration
            SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
            connManager.setDefaultSocketConfig(socketConfig);
            // Create message constraints
            MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(MAX_HEADER_COUNT)
                    .setMaxLineLength(MAX_LINE_LENGTH).build();
            // Create connection configuration
            ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction(
                    CodingErrorAction.IGNORE).setUnmappableInputAction(CodingErrorAction.IGNORE).setCharset(
                    Consts.UTF_8).setMessageConstraints(messageConstraints).build();
            connManager.setDefaultConnectionConfig(connectionConfig);
            connManager.setMaxTotal(MAX_TOTAL);
            connManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);

            // Check if use proxy
            if ((proxyURL != null && !proxyURL.trim().equals("")) && (proxyPort != null && !proxyPort.trim().equals(""))) {
                HttpHost proxy = new HttpHost(proxyURL, Integer.parseInt(proxyPort));
                DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
                httpClient = HttpClients.custom().setConnectionManager(connManager)
                        .setRetryHandler(retryHandler)
                        .setKeepAliveStrategy(keepAliveStrategy)
                        .setRedirectStrategy(redirectStrategy)
                        .setRoutePlanner(routePlanner).build();
            } else {
                httpClient = HttpClients.custom().setConnectionManager(connManager)
                        .setRetryHandler(retryHandler)
                        .setKeepAliveStrategy(keepAliveStrategy)
                        .setRedirectStrategy(redirectStrategy).build();
            }
        } catch (KeyManagementException e) {
            logger.error("KeyManagementException", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException", e);
        }
    }



    ///////////////////////////////////////////////////////////////////////////
    // <<Get>>

    /**
     * HTTP Get
     * <p/>
     * 响应内容实体采用<code>UTF-8</code>字符集
     *
     * @param url 请求url
     * @return 响应内容实体
     */
    public static String get(String url) {
        return get(url, DEFAULT_CHARSET);
    }
   
    /**
     * HTTP Get
     *
     * @param url             请求url
     * @param responseCharset 响应内容字符集
     * @return 响应内容实体
     */
    public static String get(String url, String responseCharset) {
        if (logger.isDebugEnabled()) {
            logger.debug("HTTP GET URL: " + url);
        }
        HttpGet getMethod = null;
        HttpResponse response=null;
        try {
            getMethod = new HttpGet(url);
            response = httpClient.execute(getMethod);
            return consumeResponseEntity(response, responseCharset);
        } catch (Exception e) {
            logger.error("httpclient get error: url " + url, e);
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
            }
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
        return null;
    }

   
    
    ///////////////////////////////////////////////////////////////////////////
    // <<内部辅助方法>>

    /**
     * 安全的消耗（获取）响应内容实体
     * <p/>
     * 使用 {@link org.apache.http.util.EntityUtils} 将响应内容实体转换为字符串，同时关闭输入流
     * <p/>
     * <b>注意：</b> 响应内容太长不适宜使用 EntityUtils
     *
     * @param response        HttpResponse
     * @param responseCharset 响应内容字符集
     * @return 响应内容实体
     * @throws java.io.IOException IOException
     */
    private static String consumeResponseEntity(HttpResponse response, String responseCharset) throws IOException {
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            if (logger.isDebugEnabled())
                logger.debug("Response status line: " + response.getStatusLine());

            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity, responseCharset);
            if (logger.isTraceEnabled())
                logger.trace("Response body: \n" + responseBody);
            return responseBody;
        } else {
            if (logger.isWarnEnabled())
                logger.warn("Response status line: " + response.getStatusLine());

            return null;
        }
    }

   

  

      

}
