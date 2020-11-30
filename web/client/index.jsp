
<%@ page language="java"  deferredSyntaxAllowedAsLiteral="true"  import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<title>mainBooksTore</title>
<%--导入css文件--%>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	<!-- 导入首页轮播图css和js文件 -->
	<link type="text/css" href="${pageContext.request.contextPath }/client/css/autoplay.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/autoplay.js"></script>
</head>

<body>
	<%@include file="head.jsp"%>
	<%@include file="menu_search.jsp" %>

	<div id="box_autoplay">
		<div class="list">
			<ul>
				<li><img src="${pageContext.request.contextPath }/client/ad/index_ad1.jpg" width="900" height="335" /></li>
				<li><img src="${pageContext.request.contextPath }/client/ad/index_ad2.jpg" width="900" height="335" /></li>
				<li><img src="${pageContext.request.contextPath }/client/ad/index_ad3.jpg" width="900" height="335" /></li>
				<li><img src="${pageContext.request.contextPath }/client/ad/index_ad4.jpg" width="900" height="335" /></li>
				<li><img src="${pageContext.request.contextPath }/client/ad/index_ad5.jpg" width="900" height="335" /></li>
			</ul>
		</div>
	</div>
</body>

</html>
