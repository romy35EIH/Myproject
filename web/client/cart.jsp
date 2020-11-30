<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://www.itcast.cn/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
<script>
    //当商品数量发生变化时触发该方法
	function changebookNum(count, totalCount, id) {
		count = parseInt(count);
		totalCount = parseInt(totalCount);
		//如果数量为0，判断是否要删除商品
		if (count == 0) {
			var flag = window.confirm("确认删除商品吗?");

			if (!flag) {
				count = 1;
			}
		}
		if (count > totalCount) {
			alert("已达到商品最大购买量");
			count = totalCount;
		}
		location.href = "${pageContext.request.contextPath}/changeCart?id="
				+ id + "&count=" + count;
	}
	//删除购物车中的商品
	function cart_del() {   
	    var msg = "您确定要删除该商品吗？";   
	    if (confirm(msg)==true){   
	    return true;   
	    }else{   
	    return false;   
	    }   
	}   
</script>
</head>
<body class="main">
	<p:user/>
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div class="divpagecontent">
		<div id="lcontent">
			<div class="listhead">
				<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车
			</div>
			<table cellspacing="1" class="carttable">
				<tr>
					<th class="item1">序号</th>
					<th class="item3">商品名称</th>
					<th class="item1">价格</th>
					<th class="item2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量</th>
					<th class="item1">库存</th>
					<th class="item1">小计</th>
					<th class="item1">取消</th>
				</tr>
				<!-- 循环输出商品信息 -->
				<c:set var="total" value="0" />
				<c:forEach items="${cart}" var="entry" varStatus="vs">
				<tr>
					<td class="item1">${vs.count}</td>
					<td class="item3">${entry.key.name }</td>
					<td class="item1">${entry.key.price }</td>
					<td class="item2">
						<!-- 减少商品数量 -->
						<input type="button" value='-' style="width:20px"
							   onclick="changebookNum('${entry.value-1}','${entry.key.bnum}','${entry.key.id}')">
						<!-- 商品数量显示 -->
						<input name="text" type="text" value="${entry.value}" style="width:40px;text-align:center" />
						<!-- 增加商品数量 -->
						<input type="button" value='+' style="width:20px"
							   onclick="changebookNum('${entry.value+1}','${entry.key.bnum}','${entry.key.id}')">
					</td>
					<td class="item1">${entry.key.bnum}</td>
					<td class="item1">${entry.key.price*entry.value}</td>
					<td class="item1">
						<!-- 删除商品 -->
						<a href="${pageContext.request.contextPath}/changeCart?id=${entry.key.id}&count=0"
						   style="color:#FF0000; font-weight:bold" onclick="javascript:return cart_del()">X</a>
					</td>
				</tr>
					<c:set value="${total+entry.key.price*entry.value}" var="total" />
				</c:forEach>
			</table>


			<table cellspacing="1" class="carttable">
				<tr>
					<td style="text-align:right; padding-right:40px;">
						<font style="color:#FF6600; font-weight:bold">合计：&nbsp;&nbsp;${total}元</font>
					</td>
				</tr>
			</table>
			<div style="text-align:right; margin-top:10px">
				<!--继续购物 -->
				<a href="${pageContext.request.contextPath}/showbookByPage">
					<img src="images/gwc_jx.gif" border="0" />
				</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<!--结账 -->
				<a href="${pageContext.request.contextPath}/client/order.jsp">
					<img src="${pageContext.request.contextPath}/client/images/gwc_buy.gif" border="0" />
				</a>
			</div>

		</div>
	</div>
</body>
</html>
