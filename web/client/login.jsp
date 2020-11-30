<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
</head>
<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

    <div>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div class="downbok">
				<div class="form-item">
					<input id="username" type="test" autocomplete="off" placeholder="用户ID"  name="username" />
				</div>
				<div class="form-item">
					<input id="password" name="password" type="password" autocomplete="off" placeholder="登录密码"/>
				</div>
				<div class="form-item">
					<button id="sumbit" name="image" type="image" onclick="return formcheck()" >登录</button>
				</div>
			</div>
		</form>
    </div>
</body>

</html>
