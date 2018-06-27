<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">
BODY {
	MARGIN: 0px;
	BACKGROUND-COLOR: #ffffff
}

BODY {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TD {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TH {
	FONT-SIZE: 12px;
	COLOR: #000000
}
</style>
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css">
	</HEAD>
	<body>
		<table width="100%" height="70%"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<img width="100%" src="${pageContext.request.contextPath}/img/admin/top_01.jpg">
				</td>

				<td width="100%" background="${pageContext.request.contextPath}/img/admin/top_100.jpg">
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30" valign="bottom" background="${pageContext.request.contextPath}/img/admin/mis_01.jpg">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="85%" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="#000000">
						<script type="text/javascript">
						function show(){ 
							var date = new Date(); //日期对象 
							var now = ""; 
							now = date.getFullYear()+"年"; //读英文就行了 
							now = now + (date.getMonth()+1)+"月"; //取月的时候取的是当前月-1如果想取当前月+1就可以了 
							now = now + date.getDate()+"日"; 
							now = now + date.getHours()+"时"; 
							now = now + date.getMinutes()+"分"; 
							now = now + date.getSeconds()+"秒"; 
							document.getElementById("nowDiv").innerHTML = now; //div的html是now这个字符串 
							setTimeout("show()",1000); //设置过1000毫秒就是1秒，调用show方法 
							} 
						</script> 
						<body onload="show()"> <!-- 网页加载时调用一次 以后就自动调用了--> 
						<div id="nowDiv"></div> 
					</font>
							</td>
							<td width="15%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="16"
											background="${pageContext.request.contextPath}/img/admin/mis_05b.jpg">
											<img
												src="${pageContext.request.contextPath}/img/admin/mis_05a.jpg"
												width="6" height="18">
										</td>
										<td width="155" valign="bottom"
											background="${pageContext.request.contextPath}/img/admin/mis_05b.jpg">
											用户名：
											<font color="blue"><s:property value="#session.existAdminUser.username"/></font>
										</td>
										<td width="10" align="right"
											background="${pageContext.request.contextPath}/img/admin/mis_05b.jpg">
											<img src="${pageContext.request.contextPath}/img/admin/mis_05c.jpg" width="6" height="18">
										</td>
									</tr>
								</table>
							</td>
							<td align="right" width="5%">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</HTML>
