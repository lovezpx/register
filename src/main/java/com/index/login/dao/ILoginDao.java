/**
 * 
 */
package com.index.login.dao;

import java.util.List;

import com.index.login.data.LoginData;


/**
 * <P>Title:ILoginDao </p>
 * <P>Description: </p>
 * @author zpx
 * @date 2016年6月3日 上午10:18:37
 */
public interface ILoginDao {
	/**
	 * 获取一条明细
	 * @param taskCode
	 * @return
	 */
	public List<LoginData> getUserLogin(String username, String password);
	
	public void insertUser(LoginData loginData);
}
