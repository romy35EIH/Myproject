<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="domain.User"%>
<script type="text/javascript">
//退出确认框
function confirm_logout() {   
    var msg = "您确定要退出登录吗？";   
    if (confirm(msg)==true){   
    return true;   
    }else{   
    return false;   
    }   
} 
</script>

<div class="headback">
	<img src="${pageContext.request.contextPath}/client/images/storename.jpg" />
</div>
<div id="divhead">
	<table cellspacing="0" class="headtable">
		<tr>
			<td style="text-align:right">
				<a href="${pageContext.request.contextPath}/client/cart.jsp">购物车</a>
				| <a href="#">帮助中心</a>
				| <a href="myAccount">我的帐户</a>
				<%
					User user = (User) request.getSession().getAttribute("user");
					if(null == user){
				%>
				| <a href="${pageContext.request.contextPath}/client/register.jsp">新用户注册</a>
				<%
				}else{
				%>
				| <a href="${pageContext.request.contextPath}/logout" onclick="javascript:return confirm_logout()">用户退出</a>
				<br><br><br>欢迎您： ${user.username}
				<%
					}
				%>
			</td>		
		</tr>
	</table>
</div>