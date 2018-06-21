package com.index.authority.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class EncrypUtil {
	// SecretKey 负责保存对称密钥
	private SecretKey secretKey;
	// Cipher负责完成加密或解密工作
	private Cipher cipher;
	// 该字节数组负责保存加密的结果
	private byte[] cipherByte;

	@SuppressWarnings("restriction")
	public EncrypUtil() {
		try {
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
			secretKey = generalKey();// 生成密钥
			cipher = Cipher.getInstance("AES");// 生成Cipher对象,指定其支持的DES算法
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: Encrytor
	 * @Description: 解密
	 * @param str
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws
	 */
	public byte[] Encrytor(String str) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] src = str.getBytes();
		// 加密，结果保存进cipherByte
		cipherByte = cipher.doFinal(src);
		return cipherByte;
	}
	
	/**
	 * 
	 * @Title: Decryptor
	 * @Description: 解密
	 * @param buff
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws
	 */
	public byte[] Decryptor(byte[] buff) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		cipherByte = cipher.doFinal(buff);
		return cipherByte;
	}

	/**
	 * 
	 * @Title: generalKey
	 * @Description: 获取密钥
	 * @returns@throws
	 */
	public SecretKey generalKey() {
		byte[] key = Base64.decodeBase64(AuthConfig.JWT_SECERT);
		SecretKey secretKey = new SecretKeySpec(key, 0, key.length, "AES");
		return secretKey;
	}
	
	public static void main(String[] args) throws Exception {  
		EncrypUtil encrypUtil = new EncrypUtil();  
        String msg ="郭XX-搞笑相声全集";  
        byte[] encontent = encrypUtil.Encrytor(msg);  
        byte[] decontent = encrypUtil.Decryptor(encontent);  
        System.out.println("明文是:" + msg);  
        System.out.println("加密后:" + new String(encontent));  
        System.out.println("解密后:" + new String(decontent));  
    } 
}
