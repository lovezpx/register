/**
 * 
 */
package com.index.login.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.index.login.dao.ILoginDao;
import com.index.login.data.LoginData;

/**
 * <P>Title:LoginDaoImpl </p>
 * <P>Description: </p>
 * @author zpx
 * @date 2016年6月3日 上午10:21:30
 */
@Repository(value = "LoginDaoImpl")
public class LoginDaoImpl implements ILoginDao {

	private static Logger log = Logger.getLogger(LoginDaoImpl.class);
	
	@Autowired(required=false)
	private JdbcTemplate jdbcTemplate;

	/**
	 * 获取一条明细
	 * @param taskCode
	 * @return
	 */
	public List<LoginData> getUserLogin( final String userName, final String password){
		String getUserLoginSql = "SELECT ID, USERNAME, PASSWORD, LOGINSTATUS FROM USERLOGIN " +
				 " WHERE USERNAME = ? AND PASSWORD = ? ";
		final List<LoginData> loginDatas = new ArrayList<LoginData>();
		try {
			getJdbcTemplate().query(getUserLoginSql, new Object[]{userName, password}, new RowCallbackHandler(){
				public void processRow(ResultSet rs) throws SQLException{
					LoginData loginData = new LoginData();
					loginData.setId(rs.getString("ID"));
					loginData.setUserName(rs.getString("USERNAME"));
					loginData.setPassWord(rs.getString("PASSWORD"));
					loginData.setLoginStatus(rs.getString("LOGINSTATUS"));
					loginDatas.add(loginData);
			}});
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL异常",e);
		}
		return loginDatas;
	}
	
	public void insertUser(LoginData loginData){
		String insertUserSql = "INSERT USERLOGIN (ID, USERNAME, PASSWORD, LOGINSTATUS) VALUE (?,?,?,?)";
		try {
			getJdbcTemplate().update(insertUserSql,
					new Object[]{loginData.getId(), loginData.getUserName(),loginData.getPassWord(),loginData.getLoginStatus()});

		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL异常",e);
		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
