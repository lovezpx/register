package com.index.authority.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookies工具类
 * 
 * @ClassName: CookiesUtil
 * @Description:
 * @author Zpx
 * @date 2018年4月8日
 *
 */
public class CookiesUtil {

	/**
	 * 根据名字获取cookie
	 * @Title: getCookieByName
	 * @Description: 
	 * @param request
	 * @param name
	 * @return
	 * @throws
	 */
	public Cookie getCookieByKey(HttpServletRequest request, String key) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(key)) {
			Cookie cookie = (Cookie) cookieMap.get(key);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面
	 * @Title: ReadCookieMap
	 * @Description: 
	 * @param request
	 * @return
	 * @throws
	 */
	private Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	
	/**
	 * 保存Cookies
	 * @Title: setCookie
	 * @Description: 
	 * @param response
	 * @param name
	 * @param value
	 * @param time
	 * @return
	 * @throws
	 */
	public HttpServletResponse setCookie(HttpServletResponse response, String key, String value, int time) {
		// new一个Cookie对象,键值对为参数
		Cookie cookie = new Cookie(key, value);
		// tomcat下多应用共享
		cookie.setPath("/");
		// 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
		try {
			URLEncoder.encode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		cookie.setMaxAge(time);
		// 将Cookie添加到Response中,使之生效
		response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
		return response;
	}

	/**
	 * 删除无效cookie
	 * @Title: delectCookieByName
	 * @Description: 
	 * @param request
	 * @param response
	 * @param deleteKey
	 * @throws NullPointerException
	 * @throws
	 */
	public void delectCookieByName(HttpServletRequest request, HttpServletResponse response, String key)
			throws NullPointerException {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		for (String temp : cookieMap.keySet()) {
			if (temp == key && temp.equals(key)) {
				Cookie cookie = cookieMap.get(key);
				cookie.setMaxAge(0);// 设置cookie有效时间为0
				cookie.setPath("/");// 不设置存储路径
				response.addCookie(cookie);
			}
		}
	}
}
