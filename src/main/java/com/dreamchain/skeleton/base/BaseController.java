package com.dreamchain.skeleton.base;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class BaseController {
	//定义一个全局的记录器，通过LoggerFactory获取
    protected  Logger log = LoggerFactory.getLogger(this.getClass());
    
    public BaseController(){
    	log.debug("加载："+this.getClass().getName());
    }
    
	protected String forwardTo(String location){
		return "forward:/" + location;
	}

	protected String redirectTo(String location){
		return "redirect:/" + location;
	}
	protected String getClientIP(HttpServletRequest request){
		 String remoteIp = request.getRemoteAddr();
	     String proxyIp = request.getHeader("X-Forwarded-For");
	     if (proxyIp.isEmpty()) 
	     	return remoteIp;
	     else {
	    	String forwardedIp = proxyIp;
	      	String [] ipTokens = forwardedIp.split(",");
	      	if(ipTokens != null && ipTokens.length > 1) forwardedIp = ipTokens[ipTokens.length -2].trim();
	       	return forwardedIp.isEmpty() ? remoteIp : forwardedIp;
	 	}    	
   }
   
   protected String getServerIP(){
   	try{
   		return InetAddress.getLocalHost().getHostAddress();
   	} catch(Exception ex){
   		return null;
   	}
   }
}
