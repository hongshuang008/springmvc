package com.dreamchain.skeleton.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.dreamchain.skeleton.base.BaseController;
import com.dreamchain.skeleton.bean.PMIDataBean;
import com.dreamchain.skeleton.model.AndroidAPPVersion;
import com.dreamchain.skeleton.model.DeviceVersion;
import com.dreamchain.skeleton.model.Friend;
import com.dreamchain.skeleton.model.IOSAPPVersion;
import com.dreamchain.skeleton.model.PMIData;
import com.dreamchain.skeleton.model.PMIDataCollocationForm;
import com.dreamchain.skeleton.model.Person;
import com.dreamchain.skeleton.service.impl.PMIServiceImpl;
import com.dreamchain.skeleton.service.impl.PersonServiceImpl;
import com.dreamchain.skeleton.service.impl.VersionServiceImpl;
import com.dreamchain.skeleton.util.commonUtils;

@Controller
@RequestMapping("/PMI")
public class PMIController extends BaseController{
	@Autowired
	private PMIServiceImpl pMIService;
	@Autowired
	private PersonServiceImpl personService;
	@Autowired
	private VersionServiceImpl versionService;
	
	
	
	@Value("#{propertiesReader['getPicUrl']}")
	private String getPicUrl;
	@Value("#{propertiesReader['savePicUrl']}")
	private String savePicUrl;
	
	
	
	/**
	 *  数据上传接口
	 * @param data
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> savePM2_5(PMIData data) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try {
			pMIService.save(data);
			modelMap.put("result","ok");
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	/**
	 *  数据上传接口,接收from提交
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/savePMIList", method = RequestMethod.POST, consumes="application/x-www-form-urlencoded", produces="application/json")
	@ResponseBody
	public Map<String, Object> savePMIList(PMIDataCollocationForm form) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try {
			log.debug("application/x-www-form-urlencoded");
			
			pMIService.savePMIList(form);
			modelMap.put("result","ok");
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	/**
	 * 数据上传接口,接收json
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/savePMIList", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	@ResponseBody
	public Map<String, Object> savePMIList2(@RequestBody PMIDataCollocationForm form) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try {
			log.debug("application/json");
			pMIService.savePMIList(form);
			modelMap.put("result","ok");
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	/**
	 * 数据上传接口,接收json
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public Map<String, Object> test(@RequestBody Date day) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try {
			log.debug("data:"+day);
			modelMap.put("result","ok");
			modelMap.put("data",day);
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	@RequestMapping(value = "/test2", method = RequestMethod.POST, consumes="application/json")
	@ResponseBody
	public Map<String, Object> test3(@RequestBody PMIData data) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try {
			log.debug("data:"+data.getCreateTime());
			modelMap.put("result","ok");
			modelMap.put("data",data.getCreateTime());
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	/**
	 * 数据上传接口,接收json
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.POST, consumes="application/x-www-form-urlencoded")
	@ResponseBody
	public Map<String, Object> test2(@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date day) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try {
			log.debug("data:"+day);
			modelMap.put("result","ok");
			modelMap.put("data",day);
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	/**
	 * app用户注册
	 * @param person
	 * @param imgFile
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public Map<String, Object> register(Person person) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try {
			Person person2 =  personService.findById(person.getPhone());
			if(person2!=null){
				modelMap.put("result","error");
				modelMap.put("msg","用户已存在");
			}else{
				personService.save(person);
				modelMap.put("result","ok");
			}
		} catch (Exception e) {
			log.error( this.getClass().getName(), e.getMessage());
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	/*
	 * 登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(long phone,String password) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try{	
			
			Map result = personService.login(phone,password);
			
			Map param = new HashMap<String, Object>();
			param.put("phone", phone);
			param.put("order", "id");
			param.put("limit", 1);
			List h = pMIService.getHistoryPMIData(param);
			if(h.isEmpty()){
				result.put("createTime", "");
			}else{
				HashMap<String, Object> p =  (HashMap<String, Object>) h.get(0);
				result.put("createTime", p.get("createTime"));
			}
			
			modelMap.put("result","ok");
			modelMap.put("data",result);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	/*
	 * 更新用户密码
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public Map<String, Object> updatePassword(long phone,String password,String key1,String key2) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try{	
			personService.updatePassword( phone, password, key1, key2);
			modelMap.put("result","ok");
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	
	/*
	 * 更新用户头像或昵称
	 */
	@RequestMapping("/updatePerson")
	@ResponseBody
	public Map<String, Object> updatePerson( long phone,@RequestParam(value = "nickname", required = false)String nickname,@RequestParam(value = "imgFile", required = false) MultipartFile imgFile) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		String newFileName = "";
		String oldPicName = "";
		commonUtils util = new commonUtils();
		try {
			Person person =  personService.findById(phone);
			
			if (imgFile != null && !imgFile.isEmpty()) {
				if(person.getHead_portrait()!=null){
					oldPicName = person.getHead_portrait().replace(getPicUrl, "");
				}
	            // 获取图片的文件名
	            String fileName = imgFile.getOriginalFilename();
	            // 获取图片的扩展名
	            String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
	            // 新的图片文件名 = 获取时间戳+"."图片扩展名
	            newFileName = String.valueOf(System.currentTimeMillis())
	                    + "." + extensionName;
	            util.saveFile(newFileName,extensionName, imgFile, savePicUrl);
	            person.setHead_portrait(getPicUrl+newFileName);
	            
			}
			
			if(nickname != null && !nickname.isEmpty()){
				person.setNickname(nickname);
			}
			personService.update(person);
			//如果更新成功，则执行删除旧图片
			if(!oldPicName.equals("")||!oldPicName.isEmpty()){
				util.deleteFile(oldPicName, savePicUrl);
			}
			modelMap.put("result","ok");
			modelMap.put("data",getPicUrl+newFileName);
			
		} catch (Exception e) {
			e.printStackTrace();
			//如果更新异常，则删除新图片，并事务回滚
			if(!newFileName.equals("")||!newFileName.isEmpty()){
				util.deleteFile(newFileName, savePicUrl);
			}
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	
	/*
	 * 请求添加好友，成功则返回好友昵称和头像
	 * @param phone
	 * @return
	 */
	@RequestMapping("/addFriend")
	@ResponseBody
	public Map<String, Object> addFriend(long userPhone,long friendPhone) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try {
			Map result = personService.addFriend(userPhone,friendPhone);
			modelMap.put("result","ok");
			modelMap.put("data",result);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	/*
	 * 删除好友
	 * @param phone
	 * @return
	 */
	@RequestMapping("/delFriend")
	@ResponseBody
	public Map<String, Object> delFriend(long userPhone,long friendPhone) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try {
			personService.delFriend(userPhone,friendPhone);
			modelMap.put("result","ok");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	/*
	 * 获取好友列表
	 * @param phone
	 * @return
	 */
	@RequestMapping("/getFriendsList")
	@ResponseBody
	public Map<String, Object> getFriendsList(long phone) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try {
			Person person = personService.findById(phone);
			Set<Friend> friends = person.getFriends();
			ArrayList<Map> friendsList = new ArrayList<Map>();
			for (Iterator iterator = friends.iterator(); iterator.hasNext();) {
				Friend friend = (Friend) iterator.next();
				
				Person f = friend.getFriend();
				Map result = new HashMap<String, Object>();
				result.put("phone", f.getPhone());
				result.put("nickname", f.getNickname());
				result.put("head_portrait", f.getHead_portrait());
				
				friendsList.add(result);
			}
			
			modelMap.put("result","ok");
			modelMap.put("data",friendsList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	/*
	 * 获取用户最近上传的一条PMI信息
	 */
	@RequestMapping("/getPersonPMI")
	@ResponseBody
	public Map<String, Object> getPersonPMI(long phone) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try {
			Map param = new HashMap();
			param.put("phone", phone);
			param.put("order", "id");
			param.put("limit", 1);
			List result = pMIService.getHistoryPMIData(param);
			if(result.size()>0){
				modelMap.put("result","ok");
				modelMap.put("data",result.get(0));
			}else{
				modelMap.put("result","ok");
				modelMap.put("data",null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	/*
	 * 根据时间段获取用户上传的PMI信息
	 */
	@RequestMapping("/getHistoryPMIData")
	@ResponseBody
	public Map<String, Object> getHistoryPMIData(long phone,String date_begin,String date_end) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try {
			Map param = new HashMap();
			param.put("phone", phone);
			param.put("date_begin", date_begin);
			param.put("date_end", date_end);
			
			List result = pMIService.getHistoryPMIData(param);
			modelMap.put("result","ok");
			modelMap.put("data",result);
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	}
	
	
	/*
	 * 获取版本信息
	 */
	@RequestMapping("/getVersion")
	@ResponseBody
	public Map<String, Object> getVersion(int dataType) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		try {
			switch (dataType) {
			case 1:
				AndroidAPPVersion androidAPPVersion = versionService.getLastAndroidAPPVersion();
				modelMap.put("result","ok");
				modelMap.put("data",androidAPPVersion);
				break;
			case 2:
				IOSAPPVersion iOSAPPVersion = versionService.getLastIOSAPPVersion();
				modelMap.put("result","ok");
				modelMap.put("data",iOSAPPVersion);
				break;
			case 3:
				DeviceVersion deviceVersion = versionService.getLastDeviceVersion();
				modelMap.put("result","ok");
				modelMap.put("data",deviceVersion);
				break;
			

			default:
				break;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("result","error");
			modelMap.put("msg",e.getMessage());
		}
		return modelMap;
	} 
	/**
	 * app下载页面
	 * @return
	 */
	@RequestMapping("/download")
	public String download() {
		return "download";
	}
	/**
	 * 分享页面
	 * @return
	 */
	@RequestMapping("/pmiShare")
	public String pmiShare(PMIDataBean data, Map model) {
		if(null!= data.getPm2_5()){
			Long pm2_5= Long.parseLong(data.getPm2_5());
			if(pm2_5 <= 75){
				model.put("level","good");
				model.put("levelText","优");
			}
			if(75 < pm2_5 && pm2_5 <= 150){
				model.put("level","normal");
				model.put("levelText","良");
			}
			if(150 < pm2_5){
				model.put("level","bad");
				model.put("levelText","差");
			}
			
			
		}
		
		model.put("PMIDataBean",data); 
		return "pmiShare";
	}
	
}
