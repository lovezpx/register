package com.index.bean;

/**
 * 数据中心返回类
 * @ClassName: ResultMap
 * @Description: 
 * @author Zpx
 * @date 2018年4月8日
 * 
 * @param <T>
 */
public class ResultMap<T> {

	/**
	 * 接口调用情况
	 */
	protected boolean success;

	/**
	 * 返回信息
	 */
	protected String msg;

	/**
	 * 数据源
	 */
	protected T data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultMap [success=" + success 
				+ ", data=" + data 
				+ ", msg=" + msg + "]";
	}
}
