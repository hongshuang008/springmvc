<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>欧西亚  欧爱尔手机客户端</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1" />
<style type="text/css" >
*{ margin:0; padding:0}
body,html{width:100%; height:100%}
.bodybg{background: #bfdfff;
background: -moz-radial-gradient(center, ellipse cover, #bfdfff 0%, #60abf8 44%, #4096ee 100%);
background: -webkit-gradient(radial, center center, 0px, center center, 100%, color-stop(0%,#bfdfff), color-stop(44%,#60abf8), color-stop(100%,#4096ee));
background: -webkit-radial-gradient(center, ellipse cover, #bfdfff 0%,#60abf8 44%,#4096ee 100%);
background: -o-radial-gradient(center, ellipse cover, #bfdfff 0%,#60abf8 44%,#4096ee 100%);
background: -ms-radial-gradient(center, ellipse cover, #bfdfff 0%,#60abf8 44%,#4096ee 100%);
background: radial-gradient(ellipse at center, #bfdfff 0%,#60abf8 44%,#4096ee 100%);
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#bfdfff', endColorstr='#4096ee',GradientType=1 );
}

.titles{background: rgb(255,255,255); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(255,255,255,1) 0%, rgba(243,243,243,1) 50%, rgba(237,237,237,1) 51%, rgba(255,255,255,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(255,255,255,1)), color-stop(50%,rgba(243,243,243,1)), color-stop(51%,rgba(237,237,237,1)), color-stop(100%,rgba(255,255,255,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(255,255,255,1) 0%,rgba(243,243,243,1) 50%,rgba(237,237,237,1) 51%,rgba(255,255,255,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(255,255,255,1) 0%,rgba(243,243,243,1) 50%,rgba(237,237,237,1) 51%,rgba(255,255,255,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(255,255,255,1) 0%,rgba(243,243,243,1) 50%,rgba(237,237,237,1) 51%,rgba(255,255,255,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(255,255,255,1) 0%,rgba(243,243,243,1) 50%,rgba(237,237,237,1) 51%,rgba(255,255,255,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='#ffffff',GradientType=0 ); /* IE6-9 */

width:90%; height:60px; margin:0 auto; font-family:arial,"微软雅黑"; text-align:center; font-size:20px; line-height:60px; color:#333; text-shadow:1p 2px 2px #666;
}

.downFr{ background:rgba(255,255,255,0.2);width:90%; border-bottom-left-radius:8px;border-bottom-right-radius:8px;  height:300px; border-top:solid 1px #ccc ; box-shadow:0 3px 3px rgba(0,0,0,0.2);

border:solid 1px rgba(255,255,255,0.5);

margin:0 auto!important;
}

.downFr div{
    background: rgb(109,179,242); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(109,179,242,1) 0%, rgba(84,163,238,1) 50%, rgba(54,144,240,1) 51%, rgba(30,105,222,1) 100%); /* FF3.6+ */
background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,rgba(109,179,242,1)), color-stop(50%,rgba(84,163,238,1)), color-stop(51%,rgba(54,144,240,1)), color-stop(100%,rgba(30,105,222,1))); /* Chrome,Safari4+ */
background: -webkit-linear-gradient(top,  rgba(109,179,242,1) 0%,rgba(84,163,238,1) 50%,rgba(54,144,240,1) 51%,rgba(30,105,222,1) 100%); /* Chrome10+,Safari5.1+ */
background: -o-linear-gradient(top,  rgba(109,179,242,1) 0%,rgba(84,163,238,1) 50%,rgba(54,144,240,1) 51%,rgba(30,105,222,1) 100%); /* Opera 11.10+ */
background: -ms-linear-gradient(top,  rgba(109,179,242,1) 0%,rgba(84,163,238,1) 50%,rgba(54,144,240,1) 51%,rgba(30,105,222,1) 100%); /* IE10+ */
background: linear-gradient(to bottom,  rgba(109,179,242,1) 0%,rgba(84,163,238,1) 50%,rgba(54,144,240,1) 51%,rgba(30,105,222,1) 100%); /* W3C */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#6db3f2', endColorstr='#1e69de',GradientType=0 ); /* IE6-9 */
margin:0 auto;
 display:block; width:260px; height:70px; border-radius:10px; box-shadow:0 3px 3px rgba(0,0,0,0.5); margin-top:25%;
	font-family:arial,"微软雅黑"; text-align:center; font-size:20px; line-height:70px; color:#fff;
	}
	
	.downFr div a{color:#fff; text-decoration:none; display:block}
	.downFr p{ color:#333}
.copyright{ text-align:center; height:40px; margin-top:50px; color:#fff; font-family:arial,"微软雅黑"; font-size:16px;}
</style>
<script type="text/javascript">
function fBrowserRedirect(){  
    var sUserAgent = navigator.userAgent.toLowerCase();  
    var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";    
    var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";  
    var bIsMidp = sUserAgent.match(/midp/i) == "midp";  
    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";  
    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";  
    var bIsAndroid = sUserAgent.match(/android/i) == "android";  
    var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";  
    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
    var bIsWeiXin = sUserAgent.match(/MicroMessenger/i) == "micromessenger";
    
    
    if(bIsIpad || bIsIphoneOs){//ipad or iphone
    	hideAll();
		document.getElementById('iosDown').style.display="";
    }  
    else if(bIsAndroid){ //android
    	hideAll();
		document.getElementById('androidDown').style.display="";
    }  
    else{//other
    	hideAll();
		document.getElementById('pcDown').style.display="";
    } 
    if(bIsWeiXin){
    	alert("请点击右上角，通过浏览器打开");
    }
}

function hideAll(){
	document.getElementById('pcDown').style.display="none";
	document.getElementById('androidDown').style.display="none";
	document.getElementById('iosDown').style.display="none";
}

</script> 


</head>

<body class="bodybg" onload="fBrowserRedirect();">
<div class="titles" >欧西亚  欧爱尔手机客户端</div>
<div class="downFr" id="pcDown">
   <div style="margin-top:10%!important;">
   	<!-- <a href="https://itunes.apple.com/cn/app/weather+-by-oregon-scientific/id1054327373?mt=8" data-role="button">最新iPhone版下载</a> -->
   	<a  onclick="alert('正在努力上传，请耐心等候')" href="javascript:;" data-role="button">最新iPhone版下载</a>
   </div>
   <div style="margin-top:5%!important;">
   	<!-- <a href="version-mobile!getNewApp.do?type=0&appKind=saleApp" data-role="button">最新Android版下载地址</a> -->
   	<a href="<%=path %>/AppDownload/PMI.apk" data-role="button">最新Android版下载</a>
   </div>
</div>


<div class="downFr" id="androidDown">
   <div style="margin-top:40%!important;">
   	<!-- <a href="version-mobile!getNewApp.do?type=0&appKind=saleApp" data-role="button">最新Android版下载地址</a> -->
   	<a href="<%=path %>/AppDownload/PMI.apk" data-role="button">最新Android版下载</a>
   </div>
</div>


<div class="downFr" id="iosDown">
   <div style="margin-top:40%!important;">
   	<!-- <a href="https://itunes.apple.com/cn/app/weather+-by-oregon-scientific/id1054327373?mt=8" data-role="button">最新iPhone版下载</a> -->
   	<a  onclick="alert('正在努力上传，请耐心等候')" href="javascript:;" data-role="button">最新iPhone版下载</a>
   </div>
</div>

<div class="copyright">&copy; IDT Copyright</div>

</body>
</html>

