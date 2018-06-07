/**
 * 
 */
package com.index.login.service;

import com.index.login.data.LoginInfoReturn;

/**
 * <P>Title:IUserLoginService </p>
 * <P>Description: </p>
 * @author zpx
 * @date 2016年6月3日 下午3:59:43
 */
public interface ILoginService {
	/**
	 * 登录接口
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public LoginInfoReturn login(String userName, String password);
	/**
	 * 注册接口
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public LoginInfoReturn register(String userName, String password);
}
