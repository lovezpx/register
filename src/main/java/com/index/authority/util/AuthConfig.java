package com.index.authority.util;

/**
 * 授权配置
 * @ClassName: AuthConfig
 * @Description: 
 * @author Zpx
 * @date 2018年4月8日
 *
 */
public class AuthConfig {
	//密匙
	public static final String JWT_SECERT = "J33FRx80^TOtDTW5Ob&Fwu#yPVBCOP@k";
	//token有效时间
	public static final long JWT_TTL = 1 * 60 * 60 * 1000;
	
	//成功
	public static final int RESCODE_SUCCESS = 1000;
	//成功(有返回数据)
	public static final int RESCODE_SUCCESS_DATA = 1001;
	//查询结果为空
	public static final int RESCODE_NOEXIST = 1002;
	
	//请求抛出异常
	public static final int RESCODE_EXCEPTION = 1003;
	//异常带数据
	public static final int RESCODE_EXCEPTION_DATA = 1004;
	//未登陆状态
	public static final int RESCODE_NOLOGIN = 1005;
	//无操作权限
	public static final int RESCODE_NOAUTH = 1006;
	//登录过期
	public static final int RESCODE_LOGINEXPIRE = 1007;
	
	//刷新TOKEN(有返回数据)
	public static final int RESCODE_REFTOKEN_MSG = 1008;
	//刷新TOKEN
	public static final int RESCODE_REFTOKEN = 1009;	
	
	//Token过期
	public static final int JWT_ERRCODE_EXPIRE = 1010;
	//验证不通过
	public static final int JWT_ERRCODE_FAIL = 1011;
}
