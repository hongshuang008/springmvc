<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+path+"/";
String voc2 = request.getParameter("voc");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-transform" />
  <!-- <meta http-equiv="Cache-Control" content="no-siteapp" /> -->
  <script src="<c:url value='/scripts/jquery-1.9.1.min.js'/>"></script>
  <style>
* { margin: 0; padding: 0; }  
 html, body { height: 100%; width: 100%; } 
 .good #mainDiv{background:rgb(48,216,76);}
 .good #loop{background:rgb(116 ,240 ,140);}
 .good #level{color:rgb(48,216,76)}
 
 .normal #mainDiv{background:rgb(195 ,196 ,48);}
 .normal #loop{background:rgb(226 ,227 ,79);}
 .normal #level{color:rgb(195 ,196 ,48)}
 
 .bad #mainDiv{background:rgb(226 ,92 ,5);}
 .bad #loop{background:rgb(236 ,126 ,90);}
 .bad #level{color:rgb(226 ,92 ,5);}
  </style>
 <script type="text/javascript">
	$(function(){
		/* var sUserAgent = navigator.userAgent.toLowerCase();
		var bIsWeiXin = sUserAgent.match(/MicroMessenger/i) == "micromessenger";
		if(bIsWeiXin){
	    	alert("如果网页被重新编码，请点击右上角，通过浏览器打开");
	    }  */
	    //判断浏览器语言
	    /* $("#level").text('${level}');
	    $("#level").css("font-size","60px");
	    $("#datafrom").text("The data comes from O.AIR");
    	$("#text1").text("Air Quality");
    	$("#temperature").text("Temperature（℃）");
    	$("#humidity").text("Humidity（%）"); */
    	var browserLanguage = (navigator.language || navigator.browserLanguage).toLowerCase();
	    if (browserLanguage.indexOf('zh')<=-1){
	    	$("#datafrom").text("The data comes from O.AIR");
	    	$("#text1").text("Air Quality");
	    	
	    	$("#level").text('${level}');
	    	$("#level").css("font-size","60px");
	    	$("#temperature").text("Temperature（℃）");
	    	$("#humidity").text("Humidity（%）");
	    }
	   
		//初始化函数
		$('#loop').resize();
		
	});
	$(window).resize(function(){
		$('#loop').css({
		position:'absolute',
		left: ($('#mainDiv').width()- $('#loop').outerWidth())/2,
		top:  ($('#mainDiv').height() *1.1 - $('#loop').outerHeight())*0.5 + $(document).scrollTop() 
		});
		$('#round').css({
		position:'absolute',
		left: ($('#mainDiv').width()- $('#round').outerWidth())/2,
		top:  ($('#mainDiv').height() *1.1 - $('#round').outerHeight())*0.5 + $(document).scrollTop() 
		});
		$('#text1').css({
			top:  ($('#mainDiv').height()- $('#loop').outerWidth() - $('#text1').outerHeight())*0.05
		});
		$('#text2').css({
			top:  ($('#mainDiv').height() - $('#loop').outerWidth() - $('#text2').outerHeight())*0.5*0.8
		});
		$('#level').css({
			top:  ($('#mainDiv').height() *1.1 - $('#level').outerHeight())*0.5
		});
		
		});
		 
</script>
</head>
<body style="overflow: hidden;">
<div class="${level}" style="width:100% ;height: 100%;" >
<div id="mainDiv" style="position: absolute;width:100%;height: 65%;top:0;">
	<div id="loop" style=" width:220px;height:220px;border-radius:110px;border:solid rgb(100,100,100) 0px;"></div>
	<div id="round"  style="background:#FFFFFF; width:180px;height:180px;border-radius:90px;border:solid rgb(100,100,100) 0px;z-index:2;"></div>
	
	<span id="text1" style="position: absolute;/* top:5%; */width:100% ;text-align:center; font-size:25px; color:#FFFFFF;Letter-spacing:2px;">空气质量</span>
	<span id="text2" style="position: absolute;/* top:23%; */width:100% ;text-align:center; font-size:18px; color:#FFFFFF;Letter-spacing:2px;">${PMIDataBean.city}${PMIDataBean.district}</span>
	<span id="level" style="position: absolute;/* top:43%; */width:100% ;text-align:center; font-size:70px;z-index:3;Letter-spacing:0px;">${levelText}</span>
	<span id="datafrom" style="position: absolute;right:4%;bottom:10;width:100% ;text-align:right; font-size:10px; color:#FFFFFF;Letter-spacing:2px;">此数据来自欧爱尔</span>
</div>

<table border="0" cellspacing="1" cellpadding="2" bgcolor="#CCCCCC" style="Letter-spacing:1px;position: absolute;width:100%;height: 35%;bottom:0;text-align:center;table-layout:fixed;">
	<tr>
		<td bgcolor="#FFFFFF" width="33.33%">
			<table border="0" style="width:100%;height: 100%;text-align:center;">
				<tr height="70%">
					<td style="font-size:37px;">${PMIDataBean.pm2_5}</td>
				</tr>
				<tr height="30%">
					<td style="font-size:10px;">PM2.5（ug/m³）</td>
				</tr>
			</table>
		</td>
		<td bgcolor="#FFFFFF" width="33.33%">
			<table border="0" style="width:100%;height: 100%;text-align:center;">
				<tr height="70%">
					<td style="font-size:37px;">${PMIDataBean.temp}</td>
				</tr>
				<tr height="30%">
					<td id="temperature" style="font-size:10px;">温度（℃）</td>
				</tr>
			</table>
		</td>
		<td bgcolor="#FFFFFF" width="33.33%">
			<table border="0" style="width:100%;height: 100%;text-align:center;">
				<tr height="70%">
					<td style="font-size:37px;">${PMIDataBean.humidity}</td>
				</tr>
				<tr height="30%">
					<td id="humidity" style="font-size:10px;">湿度（%）</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF" width="33.33%">
			<table border="0" style="width:100%;height: 100%;text-align:center;">
				<tr height="70%">
					<td style="font-size:37px;"><%=voc2 %></td>
				</tr>
				<tr height="30%">
					<td style="font-size:10px;">VOC（mg/m³）</td>
				</tr>
			</table>
		</td>
		<td bgcolor="#FFFFFF" width="33.33%">
			<table border="0" style="width:100%;height: 100%;text-align:center;">
				<tr height="70%">
					<td style="font-size:37px;">${PMIDataBean.pm1}</td>
				</tr>
				<tr height="30%">
					<td style="font-size:10px;">PM1.0（ug/m³）</td>
				</tr>
			</table>
		</td>
		<td bgcolor="#FFFFFF" width="33.33%">
			<table border="0" style="width:100%;height: 100%;text-align:center;">
				<tr height="70%">
					<td style="font-size:37px;">${PMIDataBean.pm10}</td>
				</tr>
				<tr height="30%">
					<td style="font-size:10px;">PM10（ug/m³）</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

</div>

</body>

</html>