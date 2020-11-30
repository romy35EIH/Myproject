<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>用户注册</title>
<%--导入css和js --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/form.js"></script>
</head>

<body class="main">
<%--传入顶部和菜单栏部分页面--%>
       <%@include file="head.jsp"%>
       <%@include file="menu_search.jsp" %>


	<div id="divcontent">
		<form action="${pageContext.request.contextPath}/register" method="post" onsubmit="return checkForm();">
			<table  class="register_table">
				<tr>
					<td class="relabel">邮箱：</td>
					<td><input type="text" class="textinput"  id="email" name="email" onkeyup="checkEmail();" placeholder="请填写有效邮箱"/></td>
				</tr>
				<tr>
					<td class="relabel">用户名：</td>
					<td ><input type="text" class="textinput"  id="username" name="username" placeholder="请输入6-10位英文字符" onkeyup="checkUsername();"/></td>
				</tr>
				<tr>
					<td class="relabel">密码：</td>
					<td><input type="password" class="textinput"  id="password" name="password" onkeyup="checkPassword();"/></td>
				</tr>
				<tr>
					<td class="relabel">确认密码：</td>
					<td><input type="password" class="textinput"  id="repassword" name="repassword" onkeyup="checkConfirm();"/></td>
					<td><span id="confirmMsg"></span>&nbsp;</td>
				</tr>
				<tr>
					<td class="relabel">性别：</td>
					<td>
						<input type="radio" name="gender"  class="sex" value="1" id="man1" />
						<label for="man1">男</label>
						<input type="radio" name="gender" class="sex" value="0" id="man2" />
						<label for="man2">女</label>
					</td>
				</tr>

				<tr>
					<td class="relabel">联系电话：</td>
					<td colspan="2"><input type="text" class="textinput" name="telephone" /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="relabel">个人介绍：</td>
					<td colspan="2"><textarea class="textarea" name="introduce"></textarea></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="70%" border="0" cellspacing="0">
				<tr>
					<td style="padding-top: 20px; text-align: center">
						<input type="image" src="images/signup.gif" name="submit" border="0" width="140" height="35"/>
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
