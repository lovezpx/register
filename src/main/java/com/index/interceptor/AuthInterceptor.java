package com.index.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.index.authority.bean.AuthResult;
import com.index.authority.bean.User;
import com.index.authority.util.AuthConfig;
import com.index.authority.util.CookiesUtil;
import com.index.authority.util.TokenUtil;
import com.index.util.GsonUtil;

import io.jsonwebtoken.Claims;

public class AuthInterceptor implements HandlerInterceptor {

	CookiesUtil cookiesUtil = new CookiesUtil();

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		Cookie token = cookiesUtil.getCookieByKey(httpServletRequest, "token");
		if (token == null) {
			request.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
			return false;
		}

		// 验证JWT的签名，返回CheckResult对象
		TokenUtil tokenUtil = new TokenUtil();
		AuthResult authResult = tokenUtil.validateJWT(token.getValue());
		if (authResult.isSuccess()) {
			Claims claims = authResult.getData();
			User user = GsonUtil.jsonStrToObject(claims.getSubject(), User.class);
			httpServletRequest.setAttribute("currentUser", user);
		} else {
			switch (authResult.getStatusCode()) {
			// 签名过期，返回过期提示码
			case AuthConfig.JWT_ERRCODE_EXPIRE:
				httpServletRequest.setAttribute("status", "jwt_errcode_expire");
				httpServletRequest.setAttribute("tokenMsg", authResult.getMsg());
				request.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
				break;
			// 签名验证不通过
			case AuthConfig.JWT_ERRCODE_FAIL:
				httpServletRequest.setAttribute("status", "jwt_errcode_fail");
				httpServletRequest.setAttribute("tokenMsg", authResult.getMsg());
				request.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
				break;
			default:
				break;
			}
		}
		
		return true;
	}

}
