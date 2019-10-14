package Guxinyu.dao;

import Guxinyu.bean.SUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户信息查询
 * @author Gu
 */
@Mapper
public interface SUserDao {
	/**
	 * 根据用户名获取用户
	 *
	 * @param name
	 * @return
	 */
	@Select(value = " SELECT su.* FROM s_user su WHERE su.name = #{name} ")
	public SUser findSUserByName(String name);

	@Insert(value=" INSERT s_user (name,password) values (#{userName},#{password})")
	public boolean addUser(String userName,String password);


}