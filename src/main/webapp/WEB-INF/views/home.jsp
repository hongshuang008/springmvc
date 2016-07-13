<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%><!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Spring MVC skeleton with Gradle build, Hibernate persistance and Selenium functional testing</title>
		<link rel="stylesheet" href="<c:url value='/styles/default.css'/>">
		<script src="<c:url value='/scripts/jquery-1.9.1.min.js'/>"></script>
		<script type="text/javascript">
			$(function (){
				//form提交时触发
				$("#postList").click(function(){
				 	
				 	var pMIDataCollocations= new Array();
				 	pMIDataCollocations[0]={phone:"123456",province:"2",city:2,district:"2",temp:"1",humidity:"2",pm1:"3",pm2_5:"4",pm10:"5",voc:"6",createTime:"2016-05-26 17:25:00"};
				 	//pMIDataCollocations.push({phone:"123456",province:"fff",city:"zz",district:"ss",temp:"1",humidity:"2",pm1:"3",pm2_5:"4",pm10:"5",voc:"6",createTime:"2016-05-26 17:25:00"});
				 	//pMIDataCollocations.push({phone:"123456",province:"fff2",city:"zz2",district:"ss2",temp:"12",humidity:"2",pm1:"3",pm2_5:"4",pm10:"5",voc:"6",createTime:"2016-05-26 17:25:00"});
				 	var data={ "pMIDataCollocations": pMIDataCollocations };
				 	
				 	
				 	var pmi = {phone:"123456",province:"2",city:2,district:"2",temp:"1",humidity:"2",pm1:"3",pm2_5:"4",pm10:"5",voc:"6" ,createTime:"2016-05-26 17:25:00" };
				 	//var day = {"day":"2016-05-30 14:19:00"};
				 	var day = "2022-05-30 14:19:00";
				 	
				 	$.ajax({
				 	  url:"http://127.0.0.1/skeleton/PMI/savePMIList",
				 	  //url:"http://127.0.0.1/skeleton/PMI/test",
				 	  type:"post",
				 	  data: JSON.stringify(data),
				 	  //data: day,
				 	  dataType:"json",
				 	  contentType:"application/json",
				 	  success:function(data){
				 		  alert(data.result);
				 	   },error:function(data){
				 		  alert(data.result);
				 	  }
				 	});


				});
				
			});
		</script>
	</head>
	<body>
		<h1>Spring MVC skeleton with Gradle build, Hibernate persistance and Selenium functional testing</h1>
		<p>Go to <a href="<c:url value='/user' />">User</a> page.</p>
		<a href="<c:url value="j_spring_security_logout" />" > Logout</a>
		
		<h3>test</h3>
		<form action="<c:url value='/PMI/test' />" method="post" >
			用户号码：<input name ="day" value="2016-05-30 15:25:00"/>
			<button type="submit">提交</button>
		</form>
		<h3>上传pimList数据</h3>
		<button id="postList">提交list</button>
		<br/>
		<h3>上传pimList数据DEMO</h3>
		<form action="<c:url value='/PMI/savePMIList' />" method="post" >
			<input name ="pMIDataCollocations[0].phone" value="13267957035" />
			<input name ="pMIDataCollocations[0].province" value="广东省"/>
			<input name ="pMIDataCollocations[0].city" value="深圳市"/>
			<input name ="pMIDataCollocations[0].district" value="宝安"/>
			<input name ="pMIDataCollocations[0].temp" value="25"/>
			<input name ="pMIDataCollocations[0].humidity" value="6"/>
			<input name ="pMIDataCollocations[0].pm1" value="5"/>
			<input name ="pMIDataCollocations[0].pm2_5" value="4"/>
			<input name ="pMIDataCollocations[0].pm10" value="3"/>
			<input name ="pMIDataCollocations[0].voc" value="2"/>
			<input name ="pMIDataCollocations[0].createTime" value="2016-05-27 17:25:00"/>
			<input name ="pMIDataCollocations[1].phone" value="13267957035" />
			<input name ="pMIDataCollocations[1].province" value="广东省2"/>
			<input name ="pMIDataCollocations[1].city" value="深圳市2"/>
			<input name ="pMIDataCollocations[1].district" value="宝安2"/>
			<input name ="pMIDataCollocations[1].temp" value="25"/>
			<input name ="pMIDataCollocations[1].humidity" value="6"/>
			<input name ="pMIDataCollocations[1].pm1" value="5"/>
			<input name ="pMIDataCollocations[1].pm2_5" value="4"/>
			<input name ="pMIDataCollocations[1].pm10" value="3"/>
			<input name ="pMIDataCollocations[1].voc" value="2"/>
			<input name ="pMIDataCollocations[1].createTime" value="2016-05-26 17:25:00"/>
			<button type="submit">提交保存</button>
		</form>
		<h3>上传pim数据DEMO</h3>
		<form action="<c:url value='/PMI/save' />" method="get">
			<input name ="phone" value="13267957035" />
			<input name ="province" value="广东省"/>
			<input name ="city" value="深圳市"/>
			<input name ="district" value="宝安"/>
			<input name ="temp" value="25"/>
			<input name ="humidity" value="6"/>
			<input name ="pm1" value="5"/>
			<input name ="pm2_5" value="4"/>
			<input name ="pm10" value="3"/>
			<input name ="voc" value="2"/>
			<input name ="createTime" value="2016-05-26 17:25:00"/>
			<button type="submit">提交保存</button>
		</form>
		<br/>
		<h3>分享pim数据</h3>
		<form action="<c:url value='/PMI/pmiShare' />" method="get">
			<input name ="phone" value="13267957035" />
			<input name ="province" value="广东省"/>
			<input name ="city" value="深圳市"/>
			<input name ="district" value="宝安"/>
			<input name ="temp" value="25"/>
			<input name ="humidity" value="6"/>
			<input name ="pm1" value="5"/>
			<input name ="pm2_5" value="4"/>
			<input name ="pm10" value="3"/>
			<input name ="voc" value="2"/>
			<input name ="createTime" value="2016-05-26 17:25:00"/>
			<button type="submit">提交保存</button>
		</form>
		<br/>
		<br/>
		<h3>用户注册DEMO</h3>
		<form action="<c:url value='/PMI/register' />" method="post" >
			<input name ="phone" value="13267957035"/>
			<input name ="password" value="123123"/>
			<input name ="nickname" value="测试测试"/>
			<input name ="key1" value="吴洪双"/>
			<input name ="key2" value="20151225"/>
			<!-- <label for="imgFile">头像</label>
			<input name="imgFile" id="imgFile" type="file" /> -->
			<button type="submit">提交</button>
		</form>
		<h3>重置密码</h3>
		<form action="<c:url value='/PMI/updatePassword' />" method="post" >
			<input name ="phone" value="13267957035"/>
			<input name ="password" value="0000"/>
			<input name ="key1" value="吴洪双"/>
			<input name ="key2" value="20151225"/>
			<button type="submit">提交</button>
		</form>
		<h3>用户登录</h3>
		<form action="<c:url value='/PMI/login' />" method="post" >
			<input name ="phone" value="13267957035"/>
			<input name ="password" value="0000"/>
			<button type="submit">登录</button>
		</form>
		<h3>更新头像昵称DEMO</h3>
		<form action="<c:url value='/PMI/updatePerson' />" method="post" enctype="multipart/form-data">
			<input name ="phone" value="13267957035"/>
			<input name ="nickname" value="修改"/>
			<label for="imgFile">头像</label>
			<input name="imgFile" id="imgFile" type="file" />
			<button type="submit">提交</button>
		</form>
		<h3>获取好友最近上传数据DEMO</h3>
		<form action="<c:url value='/PMI/getPersonPMI' />" method="post" enctype="multipart/form-data">
			好友号码：<input name ="phone" value="13267957035"/>
			<button type="submit">提交</button>
		</form>
		<h3>根据时间段获取用户上传的PMI信息</h3>
		<form action="<c:url value='/PMI/getHistoryPMIData' />" method="post">
			好友号码：<input name ="phone" value="13267957035"/>
			<input name ="date_begin" value="2016-05-16"/>
			<input name ="date_end" value="2016-05-18"/>
			<button type="submit">提交</button>
		</form>
		<h3>添加好友DEMO</h3>
		<form action="<c:url value='/PMI/addFriend' />" method="get">
			用户号码：<input name ="userPhone" value="13267957031"/>
			好友号码：<input name ="friendPhone" value="13267957035"/>
			<button type="submit">提交</button>
		</form>
		<h3>删除好友DEMO</h3>
		<form action="<c:url value='/PMI/delFriend' />" method="get">
			用户号码：<input name ="userPhone" value="13267957031"/>
			好友号码：<input name ="friendPhone" value="13267957035"/>
			<button type="submit">提交</button>
		</form>
		<h3>获取好友列表</h3>
		<form action="<c:url value='/PMI/getFriendsList' />" method="get" >
			用户号码：<input name ="phone" value="13267957031"/>
			<button type="submit">提交</button>
		</form>
		<h3>获取版本信息</h3>
		<form action="<c:url value='/PMI/getVersion' />" method="get" >
			数据类型：<input name ="dataType" value="1"/>
			<button type="submit">提交</button>
		</form>
	</body>
</html>
