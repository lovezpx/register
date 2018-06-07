/**
 * 
 */
package com.index.login.data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <P>Title:LoginData </p>
 * <P>Description: </p>
 * @author zpx
 * @date 2016年6月3日 上午10:25:31
 */
@Entity(name="UserLogin")
public class LoginData {
	
	//主键
	private String id;
	//用户名
	private String userName;
	//密码
	private String passWord;
	//登录状态
	private String loginStatus;
	
	public LoginData(){}
	
	public LoginData(String id, String userName, String passWord, String loginStatus){
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.loginStatus = loginStatus;
	}
	
	@Id
	public String getId() {
		return id==null?"":id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName==null?"":userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord==null?"":passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getLoginStatus() {
		return loginStatus==null?"":loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[id:"+id+",");
		sb.append("userName:"+userName+",");
		sb.append("passWord:"+passWord+",");
		sb.append("loginStatus:"+loginStatus+"]");
		return sb.toString();
	}
}
