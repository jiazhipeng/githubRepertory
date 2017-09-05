<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/init-taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=320, user-scalable=0, initial-scale=1,maximum-scale=1">
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no" name="format-detection"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <title>我的余额</title>
    <link rel="stylesheet" href="${ctx}/static/gcar_html/css/css.css">
    <link rel="stylesheet" href="${ctx}/static/gcar_html/css/2v.css">
    <style>
        .ticket_msg{
            background:#1f1e24;
            height:12rem;
            line-height:12rem;
            color:#fff;
            text-align:center;
        }
    </style>
</head>
<body class="greyBg">
<div class="p-info-page">
    <div class="p-info-sec">
        余额：
        <c:if test="${memberItem.balance<=99999999}"><fmt:parseNumber value="${memberItem.balance}"/></c:if>
        <c:if test="${memberItem.balance>99999999}">99999999+</c:if>
        元
    </div>
    <div class="title">余额明细</div>
    <ul class="p-info-list">

        <c:forEach items="${memberAccountLogs}" var="memberAccountLog">
            <li>
                <div class="p-row">
                    <p class="l-site date">
                        <fmt:formatDate value="${memberAccountLog.created}" pattern="yyyy.MM.dd" />
                        <c:if test="${memberAccountLog.showLasttimeModify}">
                            -
                            <fmt:formatDate value="${memberAccountLog.lasttimeModify}" pattern="yyyy.MM.dd" />
                        </c:if>

                    </p>
                    <c:if test="${memberAccountLog.powerUsedId!=null
                                    && ( memberAccountLog.transformType==4
                                            || memberAccountLog.transformType==6
                                            || memberAccountLog.transformType==8
                                            || memberAccountLog.transformType==10 ) }">
                        <a href="/wechat/mycars/toMyUsedLogView?id=${memberAccountLog.powerUsedId}" class="r-site">
                                详情
                        </a>
                    </c:if>

                </div>
                <div class="p-row">
                        ${memberAccountLog.memberItemName}
                </div>
                <div class="p-row">
                    <p class="l-site">${memberAccountLog.transformReason}</p>
                    <p class="r-site red noEllipsis">
                        <c:if test="${memberAccountLog.balance > 0}">+</c:if><fmt:parseNumber value="${memberAccountLog.balance}"/> 元
                    </p>
                </div>
                <c:if test="${memberAccountLog.total - memberAccountLog.balance != memberAccountLog.deposit }">
                <div class="p-row">
                    <p class="l-site">赠送金额</p>
                    <p class="r-site red noEllipsis">
                        +<fmt:parseNumber value="${memberAccountLog.total - memberAccountLog.balance - memberAccountLog.deposit}"/> 元
                    </p>
                </div>
                </c:if>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>