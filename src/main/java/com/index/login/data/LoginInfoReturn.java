/**
 * 
 */
package com.index.login.data;

/**
 * <P>Title:LoginInfoReturn </p>
 * <P>Description: </p>
 * @author zpx
 * @date 2016年6月3日 下午3:54:10
 */
public class LoginInfoReturn {
	public String returnCode ;

	public String returnCodeDes;

	public LoginInfoReturn(){}

	public LoginInfoReturn(String returnCode, String returnCodeDes){
		this.returnCode = returnCode;
		this.returnCodeDes = returnCodeDes;
	}

	public String getReturnCode() {
		return returnCode==null?"":returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnCodeDes() {
		return returnCodeDes==null?"":returnCodeDes;
	}

	public void setReturnCodeDes(String returnCodeDes) {
		this.returnCodeDes = returnCodeDes;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[returnCode:"+returnCode+",");
		sb.append("returnCodeDes:"+returnCodeDes+"]");
		return sb.toString();
	}
}
