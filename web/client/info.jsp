<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>山中事</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
</head>
<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div class="divpagecontent">
		<table id="lcontent">
			<tr>
				<td>
				    <div class="listhead">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
						&nbsp;&nbsp;&gt;&nbsp;
						<a href="${pageContext.request.contextPath}/showbookByPage?category=${p.category}">&nbsp;${p.category}</a>
						&nbsp;&nbsp;&gt;&nbsp;&nbsp;${p.name}
					</div>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td width="30%">
								<div class="divbookimgurl">
									<p>
										<img src="${pageContext.request.contextPath}${p.imgurl}" width="213" height="269" border="0" />
									</p>
								</div>
								<div style="text-align:center; margin-top:10px">
									<a href="${pageContext.request.contextPath}/addCart?id=${p.id}">
										<img src="${pageContext.request.contextPath }/client/images/buybutton.gif" border="0" width="100" height="25" />
									</a>
								</div>
							</td>
							<td style="padding:20px 5px 5px 5px">

								<font class="bookname">&nbsp;${p.name}</font>
								<hr />售价：<font color="#FF0000">￥${p.price}</font>
								<hr /> 类别：${p.category }
								<hr />
								<p>
									<b>内容简介：</b>
								</p> ${p.description}
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
