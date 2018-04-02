<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri='http://java.sun.com/jstl/core_rt' prefix='c'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="static_ftiao_cn" scope="request" value="http://cms.ftiao.cn" />
<c:set var="ctx" scope="request" value="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<c:if test="${fn:contains(ctx, 'localhost') }">
	<c:set var="static_ftiao_cn" scope="request" value="${ctx}" />
</c:if>
<tiles:insertAttribute name="content" />
