<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>智慧水务大平台</title>
<style>
</style>
</head>
<body>
	<div class="bg">
		<div class="main-nav">
						<div class="weather" id="weather">
				<div class="weather_box" id="weather_box">
					<div class="weatherTop" id="basic_situation">
						<div class="left_box"></div>
						<div class="right-box"></div>
					</div>
					<div class="weatherMid" id="today_forecast"></div>
					<ul class="weatherBot" id="threeDays_forecast">
					</ul>
				</div>
				<div class="set_box" id="set_box">
					<p class="weatherTop">设置</p>
					<input id="customVal" class="input_city form-control" autofocus
						placeholder="输入城市名称">
					<div class="weatherBot">
						<button class="btn" id="btn" onclick="changeCity()">完成</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 磁帖模块 -->
		<div class="main2" id="tile">
			<h4>智慧水务统一平台</h4>
			<div class="scrollBox">
				<ul class="flexBox" id="flexBox">
					
				</ul>
			</div>
		</div>
		<a href="javascript:;" class="toggle" id="toggle">
			<i class="fa fa-recycle"></i>
		</a>
		<div class="footer">
			Copyright©<%=year%>
			<span>广州市智慧水务平台</span> <span>技术支持：<span id="companyInfo">天津沃威水务科技有限公司
					浙江大学</span></span>
		</div>
	</div>
</body>
</html>

