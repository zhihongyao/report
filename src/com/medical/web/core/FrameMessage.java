package com.medical.web.core;

public class FrameMessage {

	private String systemError;
	private String systemSuccess;
	private String customError;
	private String customSuccess;
	private String customWarn;
	private String systemWarn;
	private String status;
	private Object object;
	private boolean state;
	private String message;
	private int code;
	private int errorCode;
	private int successCode;
	private int warnCode;
	public String getSystemError() {
		return systemError;
	}
	public void setSystemError(String systemError) {
		this.systemError = systemError;
	}
	public String getSystemSuccess() {
		return systemSuccess;
	}
	public void setSystemSuccess(String systemSuccess) {
		this.systemSuccess = systemSuccess;
	}
	public String getCustomError() {
		return customError;
	}
	public void setCustomError(String customError) {
		this.customError = customError;
	}
	public String getCustomSuccess() {
		return customSuccess;
	}
	public void setCustomSuccess(String customSuccess) {
		this.customSuccess = customSuccess;
	}
	public String getCustomWarn() {
		return customWarn;
	}
	public void setCustomWarn(String customWarn) {
		this.customWarn = customWarn;
	}
	public String getSystemWarn() {
		return systemWarn;
	}
	public void setSystemWarn(String systemWarn) {
		this.systemWarn = systemWarn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public int getSuccessCode() {
		return successCode;
	}
	public void setSuccessCode(int successCode) {
		this.successCode = successCode;
	}
	public int getWarnCode() {
		return warnCode;
	}
	public void setWarnCode(int warnCode) {
		this.warnCode = warnCode;
	}
	
	
}
