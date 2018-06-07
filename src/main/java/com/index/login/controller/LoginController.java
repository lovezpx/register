/**
 * 
 */
package com.index.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.index.login.data.LoginInfoReturn;
import com.index.login.service.ILoginService;

/**
 * <P>Title:LoginController </p>
 * <P>Description: </p>
 * @author zpx
 * @date 2016年5月20日 下午3:14:01
 */
@Controller
@RequestMapping(value="/")
public class LoginController {
	
	private static Logger log = Logger.getLogger(LoginController.class);
	@Resource(name = "LoginServiceImpl")
	ILoginService loginService = null;
	
	@RequestMapping(value="login.html")
	public String loginPage(){
		return "login/login";
	}
	
	@RequestMapping(value="spring.html",method=RequestMethod.POST)
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView view = new ModelAndView();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		LoginInfoReturn loginInfoReturn = (LoginInfoReturn)login(username, password);
		if(loginInfoReturn.getReturnCode().equals("CX00000")){
			//设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
			view.setViewName("menu/menu");//菜单页面
		}else{
			req.setAttribute("LoginStatus", "CX00001");
			view.setViewName("login/login");//登录页面
		}
		return view;
	}
	
	@RequestMapping(value="register.html")
	public String register(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ModelAndView view = new ModelAndView();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		LoginInfoReturn loginInfoReturn = getLoginService().register(username,password);

		return "login/login";
	}
	
	/**
	 * 登录接口   
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public LoginInfoReturn login(String userName, String password) {
		LoginInfoReturn loginInfoReturn = null;
		try{
			loginInfoReturn = getLoginService().login(userName,password);
		}catch(Exception e){
			loginInfoReturn = new LoginInfoReturn("CX100001",e.getMessage());
		}
		return loginInfoReturn;
	}
	
	public ILoginService getLoginService() {
		return loginService;
	}
	
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
}
