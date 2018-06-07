package com.index.authority.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.index.authority.util.AuthConfig;
import com.index.authority.util.CookiesUtil;
import com.index.authority.util.TokenUtil;
import com.index.authority.util.VerifyCodeUtils;
import com.index.bean.ResultMap;
import com.index.bean.authority.User;


/**
 * 登录入口
 * 
 * @ClassName: LoginController
 * @Description:
 * @author Zpx
 * @date 2018年4月8日
 *
 */
@Controller
public class LoginController {

	private Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(method = RequestMethod.GET, value = "login")
	public String login() {
		return "authority/login";
	}

	@RequestMapping(value = "submit")
	@ResponseBody
	public ResultMap submit(HttpServletResponse response, User user) {
		ResultMap resultMap = new ResultMap();
		
		resultMap.setSuccess(true);
		TokenUtil tokenUtil = new TokenUtil();
		CookiesUtil cookiesUtil = new CookiesUtil();
		
		String tokenSub = tokenUtil.getSubject(user);
		String token = tokenUtil.InitJWT(tokenSub);
		cookiesUtil.setCookie(response, "token", token, new Long(AuthConfig.JWT_TTL).intValue());
		return resultMap;
	}

	@RequestMapping(value = "getVerifyCode")
	@ResponseBody
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html");
		VerifyCodeUtils verifyCodeUtils = new VerifyCodeUtils(110, 40);
		File dir = new File("D:/upload/verifycode");
		String verifyCode = verifyCodeUtils.generateVerifyCode(4);
		File file = new File(dir, verifyCode + ".jpg");
		try {
			verifyCodeUtils.outputImage(file, verifyCode);

			PrintWriter out = response.getWriter();
			out.write(verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
