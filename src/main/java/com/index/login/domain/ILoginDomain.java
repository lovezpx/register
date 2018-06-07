/**
 * 
 */
package com.index.login.domain;

import com.index.login.data.LoginInfoReturn;

/**
 * <P>Title:ILoginDomain </p>
 * <P>Description: </p>
 * @author zpx
 * @date 2016年6月3日 下午4:24:19
 */
public interface ILoginDomain {

	public LoginInfoReturn login(String userName, String password);
	
	public LoginInfoReturn register(String userName, String password);
}
