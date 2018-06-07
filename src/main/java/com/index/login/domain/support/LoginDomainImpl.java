/**
 * 
 */
package com.index.login.domain.support;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.index.login.dao.ILoginDao;
import com.index.login.data.LoginData;
import com.index.login.data.LoginInfoReturn;
import com.index.login.domain.ILoginDomain;
import com.index.login.mapper.IGetPersonInfoMapper;

/**
 * <P>Title:LoginDomainImpl </p>
 * <P>Description: </p>
 * @author zpx
 * @date 2016年6月3日 下午4:28:32
 */
@Repository(value = "LoginDomainImpl")
public class LoginDomainImpl implements ILoginDomain {

	private static Logger log = Logger.getLogger(LoginDomainImpl.class);
	@Resource(name = "LoginDaoImpl")
	ILoginDao loginDao = null;
	@Resource(name = "getPersonInfoMapper")
	IGetPersonInfoMapper iGetPersonInfoMapper = null;
	
	@Override
	public LoginInfoReturn login(String userName, String password) {
		
		LoginInfoReturn loginInfoReturn = new LoginInfoReturn();

		try{
			List<LoginData> loginDatas = getLoginDao().getUserLogin(userName,password);
			
			if(userName.equals(loginDatas.get(0).getUserName())&&password.equals(loginDatas.get(0).getPassWord())){
				loginInfoReturn.setReturnCode("CX00000");
				loginInfoReturn.setReturnCodeDes("登陆成功");
			}else {
				loginInfoReturn = new LoginInfoReturn("CX00001","操作失败");
			}
		}catch(Exception e){
			loginInfoReturn.setReturnCode("CX10005");
			loginInfoReturn.setReturnCodeDes(e.getMessage());
			log.warn(e);
		}
		return loginInfoReturn;
	}
	
	@Override
	public LoginInfoReturn register(String userName, String password) {
			
		LoginInfoReturn loginInfoReturn = new LoginInfoReturn();

		try{
			LoginData loginData = new LoginData();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String time = "00000000000000";
			time = sdf.format(new Date());
			
			loginData.setId(time);
			loginData.setUserName(userName);
			loginData.setPassWord(password);
			loginData.setLoginStatus("正常");
			getLoginDao().insertUser(loginData);
		}catch(Exception e){
			loginInfoReturn.setReturnCode("CX10005");
			loginInfoReturn.setReturnCodeDes(e.getMessage());
			log.warn(e);
		}
		return loginInfoReturn;
	}

	public ILoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(ILoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
}
