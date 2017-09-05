package com.hy.test.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
 
/**
 * config.properties文件映射类
 * @author luolin
 *
 * @version $id:ConfigProperty.java,v 0.1 2015年8月7日 下午2:10:44 luolin Exp $
 */
@Component("configProperty")
public class ConfigProperty {
 
    /** 作者名字 */
    @Value("#{setting[author_name]}")
    private String authorName;
    /** 项目信息 */
    @Value("#{setting[project_info]}")
    private String projectInfo;
    
    /** mysql jdbc url */
    @Value("#{setting[jdbcurl_info]}")
    private String jdbcUrl;
    
    public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	/**
     * @return the authorName
     */
    public String getAuthorName() {
        return authorName;
    }
 
    /**
     * @param authorName the authorName to set
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
 
    /**
     * @return the projectInfo
     */
    public String getProjectInfo() {
        return projectInfo;
    }
 
    /**
     * @param projectInfo the projectInfo to set
     */
    public void setProjectInfo(String projectInfo) {
        this.projectInfo = projectInfo;
    }
 
}
