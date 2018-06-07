/**
 * 
 */
package com.index.login.service.support;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.index.login.data.LoginInfoReturn;
import com.index.login.domain.ILoginDomain;
import com.index.login.service.ILoginService;

/**
 * <P>Title:LoginServiceImpl </p>
 * <P>Description: </p>
 * @author zpx
 * @date 2016年6月3日 下午4:13:53
 */
@Repository(value = "LoginServiceImpl")
public class LoginServiceImpl implements ILoginService {

	private static Logger log = Logger.getLogger(LoginServiceImpl.class);
	@Autowired
	private TransactionTemplate transactionTemplate;
	@Resource(name = "LoginDomainImpl")
	private ILoginDomain loginDomain = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public LoginInfoReturn login(final String userName, final String password) {
		return (LoginInfoReturn)transactionTemplate.execute(
				new TransactionCallback(){public Object doInTransaction(TransactionStatus arg0) {
				return getLoginDomain().login(userName, password);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public LoginInfoReturn register(final String userName, final String password){
		return (LoginInfoReturn)transactionTemplate.execute(
				new TransactionCallback(){public Object doInTransaction(TransactionStatus arg0) {
				return getLoginDomain().register(userName, password);
			}
		});
	}

	public ILoginDomain getLoginDomain() {
		return loginDomain;
	}

	public void setLoginDomain(ILoginDomain loginDomain) {
		this.loginDomain = loginDomain;
	}
	
}
