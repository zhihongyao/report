package com.medical.web.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 
 * Copyright (c) 2015-2016 EI. All Rights Reserved.
 *
 * @ClassName: BaseController 
 * @Description: TODO(SpringMVC Controller 基类) 
 * @author Mearo.Yi 
 * @date 2016年11月18日 上午12:15:31 
 *
 */
public class BaseController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * ThreadLocal确保高并发下每个请求的request，response都是独立的
	 */
	private static ThreadLocal<ServletRequest> currentRequest = new ThreadLocal<ServletRequest>();
	private static ThreadLocal<ServletResponse> currentResponse = new ThreadLocal<ServletResponse>();
	//private static ThreadLocal<CommonParams> currentParams = new ThreadLocal<CommonParams>();
	//private static ThreadLocal<CommonParams> currentParams = new ThreadLocal<CommonParams>();

	/**
	 * 线程安全初始化request，respose对象
	 * 
	 * @param request
	 * @param response
	 * @date 2016年11月18日
	 * @author Mearo.Yi
	 */
	@ModelAttribute
	public void initReqAndRep(HttpServletRequest request, HttpServletResponse response) {
		currentRequest.set(request);
		currentResponse.set(response);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(int.class, new IntEditor());  
	}
	
	
//	@ModelAttribute
//	public void initCommonParams(String para) {
//		if (StringUtils.isNotEmpty(para)) {
//			try {
//				CommonParams commonParams = JsonUtil.formJson(para, CommonParams.class);
//				currentParams.set(commonParams);
//				Gson gson = new GsonBuilder().setPrettyPrinting().create();
//				logger.info( gson.toJson(commonParams));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	/**
//	 * 获取app传递的params参数
//	 * 
//	 * @return
//	 * @date 2015年11月24日
//	 * @author 漂泊者及其影子
//	 */
//	public CommonParams getParams() {
//		return currentParams.get();
//	}
	
	/**
	 * 
	 * @param mv
	 * @return
	 * @date 2015年11月18日
	 * @author Mearo.Yi
	 */
//	public ModelAndView modelAndView(ModelAndView mv) {
//		if (mv.getModel().get("state") == null) {
//			mv.noticeSuccess();
//		}
//
//		if (!mv.hasView()) {
//			mv.setView(new MappingJacksonJsonView());
//		}
//		return mv;
//
//	}
	
	// html输出输出，返回null
	 /**  
     * AJAX输出，返回null  
     * @param content - 输出内容  
     * @return type - 输出类型  
     */  
	public String ajax(JSONObject content, String type) {
		try {
			HttpServletResponse response = (HttpServletResponse) currentResponse.get();
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().print(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**  
     * AJAX输出，返回null  
     * @param content - 输出内容  
     * @return type - 输出类型  
     */  
	public String ajaxForArray(JSONArray jsonArray, String type) {
		try {
			HttpServletResponse response = (HttpServletResponse) currentResponse.get();
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().print(jsonArray);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 /**  
     * AJAX输出，返回null  
     * @param content - 输出内容  
     * @return type - 输出类型  
     */  
	public String ajaxForString(String content, String type) {
		try {
			HttpServletResponse response = (HttpServletResponse) currentResponse.get();
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
  
    /**  
     * AJAX输出文本，返回null  
     * @param test - 文本内容  
     * @return String - String  
     */  
    public String ajaxText(String text) {
        return ajaxForString(text, "text/plain");
    }
  
    /**  
     * AJAX输出HTML，返回null  
     * @param html - html内容  
     * @return String - String  
     */  
	public String ajaxHtml(String html) {
		return ajaxForString(html, "text/html");
	}
  
    /**  
     * AJAX输出XML，返回null  
     * @param xml - xml  
     * @return String - String  
     */  
	public String ajaxXml(String xml) {
		return ajaxForString(xml, "text/xml");
	}
  
    /**  
     * 根据字符串输出JSON，返回null  
     * @param jsonString - 字符串内容  
     * @return String - 处理过后的String  
     */  
	public String ajaxJson(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return ajax(jsonObject, "application/json");
	}
       
    /**  
     * 根据Map输出JSON，返回null  
     * @param jsonMap - map   
     * @return String - json形式的字符串  
     */  
	public String ajaxJson(Map jsonMap) {
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return ajax(jsonObject, "application/json");
	}
	
	public String ajaxJson(JSONArray array) {
		JSONArray jsonArray = JSONArray.fromObject(array);
		return ajaxForArray(jsonArray, "application/json");
	}
    
	/**  
     * 输出JSON成功数据，返回null  
     * @param jsonMap - map   
     * @return String - json形式的字符串  
     */  
	public String ajaxJsonSuccessData(Object jsonObject, Object... obj) {
		FrameMessage fm = new FrameMessage();
		fm.setStatus(Constants.SUCCESS);
		fm.setObject(jsonObject);
		JSONObject jsonObj = null;
		boolean isFormat = false;
		JsonConfig cfg = null;
		if(obj.length == 0){
			 jsonObj = JSONObject.fromObject(fm);
		}
		for(int i = 0; i < obj.length; i++){
			//对象String代表自定义消息
			if(obj[i] instanceof java.lang.String){
				if((obj[i]+"").startsWith(Constants.SYSTEM_MESSAGE)){
					fm.setSystemSuccess((obj[i]+"").substring(Constants.SYSTEM_MESSAGE.length()));
				}else{
					fm.setCustomSuccess(obj[i]+"");
				}
			//对象Integer代表状态码
			}else if(obj[i] instanceof java.lang.Integer){
				fm.setSuccessCode(Integer.parseInt(obj[i]+""));
			//对象如果JsonConfig代表JSON格式化
			}else if(obj[i] instanceof net.sf.json.JsonConfig){
				cfg = (JsonConfig) obj[i];
				isFormat = true;
			}
		}
		if(isFormat){ //是否格式化JSON
			jsonObj = JSONObject.fromObject(fm, cfg);
		}else{
			jsonObj = JSONObject.fromObject(fm);
		}
		return ajax(jsonObj, "application/json");
	}
	
	/**  
     * 输出JSON警告数据，返回null  
     * @param jsonMap - map   
     * @return String - json形式的字符串  
     */  
	public String ajaxJsonWarnData(Object jsonObject, Object... obj) {
		FrameMessage fm = new FrameMessage();
		fm.setStatus(Constants.WARN);
		fm.setObject(jsonObject);
		JSONObject jsonObj = null;
		boolean isFormat = false;
		JsonConfig cfg = null;
		if(obj.length == 0){
			 jsonObj = JSONObject.fromObject(fm);
		}
		for(int i = 0; i < obj.length; i++){
			//对象String代表自定义消息
			if(obj[i] instanceof java.lang.String){
				if((obj[i]+"").startsWith(Constants.SYSTEM_MESSAGE)){
					fm.setSystemWarn((obj[i]+"").substring(Constants.SYSTEM_MESSAGE.length()));
				}else{
					fm.setCustomWarn(obj[i]+"");
				}
			//对象Integer代表状态码
			}else if(obj[i] instanceof java.lang.Integer){
				fm.setWarnCode(Integer.parseInt(obj[i]+""));
			//对象如果JsonConfig代表JSON格式化
			}else if(obj[i] instanceof net.sf.json.JsonConfig){
				cfg = (JsonConfig) obj[i];
				isFormat = true;
			}
		}
		if(isFormat){ //是否格式化JSON
			jsonObj = JSONObject.fromObject(fm, cfg);
		}else{
			jsonObj = JSONObject.fromObject(fm);
		}
		return ajax(jsonObj, "application/json");
	}
	
	/**  
     * 输出JSON失败数据，返回null  
     * @param jsonMap - map   
     * @return String - json形式的字符串  
     */ 
	public String ajaxJsonErrorData(Object jsonObject, Object... obj) {
		FrameMessage fm = new FrameMessage();
		fm.setStatus(Constants.ERROR);
		fm.setObject(jsonObject);
		JSONObject jsonObj = null;
		boolean isFormat = false;
		JsonConfig cfg = null;
		if(obj.length == 0){
			 jsonObj = JSONObject.fromObject(fm);
		}
		for(int i = 0; i < obj.length; i++){
			//对象String代表自定义消息
			if(obj[i]  instanceof java.lang.String){
				if((obj[i]+"").startsWith(Constants.SYSTEM_MESSAGE)){
					fm.setSystemError((obj[i]+"").substring(Constants.SYSTEM_MESSAGE.length()));
				}else{
					fm.setCustomError(obj[i]+"");
				}
			//对象Integer代表状态码
			}else if(obj[i] instanceof java.lang.Integer){
				fm.setErrorCode(Integer.parseInt(obj[i]+""));
			//对象如果JsonConfig代表JSON格式化
			}else if(obj[i] instanceof net.sf.json.JsonConfig){
				cfg = (JsonConfig) obj[i];
				isFormat = true;
			}
		}
		if(isFormat){ //是否格式化JSON
			jsonObj = JSONObject.fromObject(fm, cfg);
		}else{
			jsonObj = JSONObject.fromObject(fm);
		}
		return ajax(jsonObj, "application/json");
	}
	
	/**  
     * 输出JSON警告消息，返回null  
     * @param message - 消息内容  
     * @return String - json处理过后的字符串  
     */  
	public String ajaxJsonWarnMessage(Object... obj) {
		FrameMessage fm = new FrameMessage();
		fm.setStatus(Constants.WARN);
		if(obj.length == 1){
			fm.setCustomWarn(obj[0]+"");
		}else if(obj.length == 2){
			fm.setCustomWarn(obj[0]+"");
			fm.setSystemWarn(obj[1]+"");
		}
		JSONObject jsonObject = JSONObject.fromObject(fm);
		return ajax(jsonObject, "application/json");
	}
       
    /**  
     * 输出JSON成功消息，返回null  
     * @param message - 消息内容  
     * @return String - json格式后的内容  
     */  
	public String ajaxJsonSuccessMessage(Object... obj) {
		FrameMessage fm = new FrameMessage();
		fm.setStatus(Constants.SUCCESS);
		if(obj.length == 1){
			fm.setCustomSuccess(obj[0]+"");
		}else if(obj.length == 2){
			fm.setCustomSuccess(obj[0]+"");
			fm.setSystemSuccess(obj[1]+"");
		}
		JSONObject jsonObject = JSONObject.fromObject(fm);
		return ajax(jsonObject, "application/json");
	}
       
    /**  
     * 输出JSON错误消息，返回null  
     * @param message - 错误消息内容  
     * @return String - json格式后的字符串  
     */  
	public String ajaxJsonErrorMessage(Object... obj) {
		FrameMessage fm = new FrameMessage();
		fm.setStatus(Constants.ERROR);
		if(obj.length == 1){
			fm.setCustomError(obj[0]+"");
		}else if(obj.length == 2){
			fm.setCustomError(obj[0]+"");
			fm.setSystemError(obj[1]+"");
		}
		JSONObject jsonObject = JSONObject.fromObject(fm);
		return ajax(jsonObject, "application/json");
	}
	
	 // 设置页面不缓存
	public void setResponseNoCache(HttpServletResponse response) {
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
	}
	
	/**
	 * 线程安全
	 * 
	 * @return
	 * @date 2016年11月18日
	 * @author Mearo.Yi
	 */
	public HttpServletRequest request() {
		return (HttpServletRequest) currentRequest.get();
	}

	/**
	 * 线程安全
	 * 
	 * @return
	 * @date 2016年11月18日
	 * @author Mearo.Yi
	 */
	public HttpServletResponse response() {
		return (HttpServletResponse) currentResponse.get();
	}
	 
}
