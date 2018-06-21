<!DOCTYPE html>

<%@ page language="java"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
<base href="<%=basePath%>" />
<meta charset="UTF-8" />

<title>辽宁工贸学校登录页</title>

<link rel="stylesheet" href="Plugins/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="Plugins/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" href="css/authority/login.css">
<script src="Plugins/jquery/jquery-1.10.1.js"></script>
<script src="js/login/login.js"></script>
<script src="Plugins/jquery/jquery.cookie.js" type="text/javascript"></script>
<script src="js/common/waterUtils.js" type="text/javascript"></script>

</head>
<body>
	<div class="login">
		<div class="slideshow">
			<img class="bg bg1 active" src="image/login/bg1.jpg" alt=""> <img
				class="bg bg2" src="image/login/bg2.jpg" alt="">
		</div>
		<div class="content">
			<div class="left-box">
				<img class="left-img left-img1" src="image/login/login1.png">
				<img class="left-img left-img2" src="image/login/login2.png">
			</div>
			<div class="right-box">
				<div class="login-box">
					<span class="txt">用户登录</span>
					<p class="p-productName">欢迎使用沃威智慧水务</p>
					<p class="errorTip" id="tip"></p>
					<p class="p-input">
						<i class="fa fa-user icon-color"></i> <input class="form-control"
							size="25" id="account" placeholder="请输入账号" />
					</p>
					<p class="p-input">
						<i class="fa fa-lock icon-color"></i> <input class="form-control"
							type="password" id="password" size="25" placeholder="请输入密码" />
					</p>
					<p class="p-input" id="validatecode_p">
						<input type="text" id="validateMsg" name="validateMsg"
							class="form-control idCode"> <img
							class="pull-right codeImg" id="codeImg" src="">
					</p>
					<p class="p-password pull-right">
						<input type="checkbox" value="1" id="remenber" name="remenber" />
						<label for="remenber"> <span>记住密码</span>
						</label>
					</p>
					<input name="submit" id="loginBtn" type="button" class="login-btn btn btn-block"
						value="登  录"/>
				</div>
			</div>
		</div>
		<div class="qrcode">
			<span class="icon-item icon-item-weixin"> <i
				class="fa fa-weixin"></i> <img src="image/login/weichat.png"
				class="qrcode-weixin">
			</span> <span class="icon-item icon-item-app"> <i
				class="fa fa-tablet"></i> <img src="image/login/app.png"
				class="qrcode-app">
			</span>
		</div>
		<div class="bottom-mask">
			<span>辽宁工贸学校</span> <span>小小宁一责任有限公司</span>
		</div>
	</div>
</body>
</html>