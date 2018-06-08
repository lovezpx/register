package com.index.authority.bean;

import com.index.bean.ResultMap;
import com.index.util.GsonUtil;

import io.jsonwebtoken.Claims;

public class AuthResult extends ResultMap<Claims> {
	private int statusCode;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public AuthResult(){}
	
	public AuthResult(boolean success, String msg){
		super();
		this.success = success;
		this.msg = msg;
	}
	
	public AuthResult(boolean success, String msg, Claims data){
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}
	
	/**
	 * 请求返回数据处理
	 * @param ResultMap
	 * @return
	 */
	public String getJsonStr() {
		return GsonUtil.objectToJsonStr(this);
	}
}
