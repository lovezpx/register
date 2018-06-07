/**
 * 
 */
package com.index.login.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.index.login.data.LoginData;

/**
 * <P>Title:IGetPersonInfoMapper </p>
 * <P>Description: </p>
 * @author zpx
 * @date 2017年5月3日 下午8:43:38
 */
@Repository(value = "getPersonInfoMapper")
public interface IGetPersonInfoMapper {
	/**
     * 查询所有的用户信息
     * @return
     * @throws Exception
     */
   public List<LoginData> selectAllUser() throws Exception;
}
