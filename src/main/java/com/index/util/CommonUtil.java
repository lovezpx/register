package com.index.util;

import java.util.UUID;

/**
 * 
 * @ClassName: CommonUtil
 * @Description: 公共工具类
 * @author Zpx
 * @date 2018年4月8日
 *
 */
public class CommonUtil {
	
	/**
	 * commonUtil
	 * @Title: getGUID
	 * @Description: 生成Guid
	 * @return
	 * @throws
	 */
	public String getGUID() {
		String str = UUID.randomUUID().toString();
		String guid = str.replaceAll("-", "");
		return guid;
	}
	
	/**
	 * commonUtil
	 * @Title: getUUID
	 * @Description: 生成uuid
	 * @return
	 * @throws
	 */
	public String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	@SuppressWarnings("unused")
	public static void main(String []ags) {
		CommonUtil commonUtil = new CommonUtil();
		System.out.println();
	}
}
