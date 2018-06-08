package com.index.authority.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.index.authority.bean.User;

/**
 * 
 * @ClassName: IGetPersonInfoMapper
 * @Description: 
 * @author Zpx
 * @date 2018年6月8日
 *
 */
@Repository(value = "authorityMapper")
public interface IAuthorityMapper {
	/**
     * 查询所有的用户信息
     * @return
     * @throws Exception
     */
   public List<User> selectAllUser() throws Exception;
}
