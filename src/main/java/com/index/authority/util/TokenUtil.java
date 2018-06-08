package com.index.authority.util;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.index.authority.bean.AuthResult;
import com.index.authority.bean.User;
import com.index.bean.ResultMap;
import com.index.util.CommonUtil;
import com.index.util.GsonUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * 
 * @ClassName: TokenUtil
 * @Description: 
 * @author Zpx
 * @date 2018年6月8日
 *
 */
public class TokenUtil {
	
	CommonUtil commonUtil = new CommonUtil();
	
	/**
	 * 获取密钥
	 * @Title: generalKey
	 * @Description: 
	 * @return
	 * @throws
	 */
	public SecretKey generalKey() {
		byte[] key = Base64.decodeBase64(AuthConfig.JWT_SECERT);
	    SecretKey secretKey = new SecretKeySpec(key, 0, key.length, "AES");
	    return secretKey;
	}
	
	/**
	 * 签发JWT
	 * @Title: InitJWT
	 * @Description: 
	 * @param subject
	 * @param ttlMillis
	 * @return
	 * @throws
	 */
	public String InitJWT(String subject) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		String jwtId = commonUtil.getGUID();
		
		SecretKey secretKey = generalKey();
		JwtBuilder builder = Jwts.builder()
				.setId(jwtId)
				.setSubject(subject)
				.setIssuedAt(now)
				.signWith(signatureAlgorithm, secretKey);
		
		if (AuthConfig.JWT_TTL >= 0) {
			long expMillis = nowMillis + AuthConfig.JWT_TTL;
			Date expirationDate = new Date(expMillis);
			builder.setExpiration(expirationDate);
		}
		
		return builder.compact();
	}
	
	/**
	 * 验证JWT
	 * @Title: validateJWT
	 * @Description: 
	 * @param jwtStr
	 * @return
	 * @throws
	 */
	public AuthResult validateJWT(String jwtStr) {
		AuthResult result = new AuthResult();
		Claims claims = null;
		try {
			claims = parseJWT(jwtStr);
			result.setStatusCode(AuthConfig.RESCODE_SUCCESS_DATA);
			result.setSuccess(true);
			result.setData(claims);
		} catch (ExpiredJwtException e) {
			result.setStatusCode(AuthConfig.JWT_ERRCODE_EXPIRE);
			result.setMsg("会话超时，请重新登录！");
			result.setSuccess(false);
		} catch (SignatureException e) {
			result.setStatusCode(AuthConfig.JWT_ERRCODE_FAIL);
			result.setMsg("登录验证出错，请重新登录！");
			result.setSuccess(false);
		} catch (Exception e) {
			result.setStatusCode(AuthConfig.JWT_ERRCODE_FAIL);
			result.setMsg("登录验证出错，请重新登录！");
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 解析JWT字符串
	 * @Title: parseJWT
	 * @Description: 
	 * @param jwt
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public Claims parseJWT(String jwt) throws Exception {
		SecretKey secretKey = generalKey();
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(jwt)
			.getBody();
	}
	
	/**
	 * 生成subject信息
	 * @Title: generalSubject
	 * @Description: 
	 * @param sub
	 * @return
	 * @throws
	 */
	public String getSubject(User user){
		return GsonUtil.objectToJsonStr(user);
	}
	
	public static void main(String []ags) throws Exception {
		TokenUtil tokenUtil = new TokenUtil();
		
		User user = new User();
		user.setAccount("admin");
		user.setPassword("test123");
		
		String sub = tokenUtil.getSubject(user);
		System.out.println(sub);
		
		String jwt = tokenUtil.InitJWT(sub);
		System.out.println(jwt);
		
		ResultMap<Claims> result = tokenUtil.validateJWT(jwt);
		System.out.println(result.getData());
		
		Claims subt = tokenUtil.parseJWT(jwt);
		System.out.println(subt);
		
	}
}
