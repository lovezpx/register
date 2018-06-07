package com.index.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @ClassName: PropertiesUtil
 * @Description: 读取配置文件公共方法
 * @author Zpx
 * @date 2017年9月5日
 *
 */
public class PropertiesUtil {
	
	/**
	 * 
	 * @Title: GetValueByKey
	 * @Description: 根据Key读取Value
	 * @param filePath
	 * @param key
	 * @return String
	 * @throws
	 */
	public String GetValueByKey(String filePath, String key) {
		Properties properties = new Properties();
		try {
			InputStreamReader inputStream = new InputStreamReader(new FileInputStream(filePath),"UTF-8");
			properties.load(inputStream);
			String value = properties.getProperty(key);
			return value;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @Title: GetAllProperties
	 * @Description: 读取Properties的全部信息
	 * @param filePath
	 * @throws IOException
	 * @return Map<String,String>
	 */
	public Map<String, String> GetAllProperties(String filePath) throws IOException {
		Properties properties = new Properties();
		InputStreamReader inputStream = new InputStreamReader(new FileInputStream(filePath),"UTF-8");
		properties.load(inputStream);
		Enumeration en = properties.propertyNames(); // 得到配置文件的名字

		Map<String, String> map = new HashMap<String, String>();
		while (en.hasMoreElements()) {
			String strKey = (String) en.nextElement();
			String strValue = properties.getProperty(strKey);
			
			map.put(strKey, strValue);
		}
		
		return map;
	}

	/**
	 * 
	 * @Title: WriteProperties
	 * @Description: 写入Properties信息
	 * @param filePath
	 * @param pKey
	 * @param pValue
	 * @throws IOException
	 * @return void
	 */
	public void WriteProperties(String filePath, String pKey, String pValue) throws IOException {
		Properties properties = new Properties();

		InputStreamReader inputStream = new InputStreamReader(new FileInputStream(filePath),"UTF-8");
		// 从输入流中读取属性列表（键和元素对）
		properties.load(inputStream);
		// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
		// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
		OutputStream out = new FileOutputStream(filePath);
		properties.setProperty(pKey, pValue);
		// 以适合使用 load 方法加载到 Properties 表中的格式，
		// 将此 Properties 表中的属性列表（键和元素对）写入输出流
		properties.store(out, "Update " + pKey + " name");
	}
}
