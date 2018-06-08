package com.index.authority.bean;

public class User {
	
	/**
	 * UUID
	 */
	private String id;
	
	/**
	 * 登录账户
	 */
	private String account;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 用户密码
	 */
	private String password;
	
	/**
	 * 联系方式
	 */
	private String telephone;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 用户状态
	 */
	private int status;
	
	/**
	 * 创建人
	 */
	private String createby;
	
	/**
	 * 创建时间
	 */
	private String createdate;
	
	/**
	 * 更新人
	 */
	private String updateby;
	
	/**
	 * 更新时间
	 */
	private String updatedate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
}
