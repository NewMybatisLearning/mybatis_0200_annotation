package com.ymd.learn.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.ymd.learn.entity.User;

public interface UserMapper {
	
	@Select("select id, username, sex, birthday, address from t_user where id = #{id}")
	public User selectUserById(int id) throws Exception;
	
	@Select("select * from t_user")
	public List<User> selectUserAll() throws Exception;
	
	@Select("select id, username, sex, birthday, address from t_user where username like '%${value}%'")
	public List<User> SelectLikeUsername(String username) throws Exception;
	
	@SelectKey(keyProperty="id", resultType=Integer.class, before=false, statement={"select LAST_INSERT_ID()"})
	@Insert("insert into t_user(id, username, sex, birthday, address) values (#{id}, #{username}, #{sex}, #{birthday}, #{address})")
	public void insertUser(User user) throws Exception;
	
	@Update("update t_user set username=#{username}, sex=#{sex}, birthday=#{birthday}, address=#{address} where id=#{id}")
	public void updateUserById(User user) throws Exception;
	
	@Delete("delete from t_user where id = #{id}")
	public void deleteUserById(int id) throws Exception;
	
	@Select("select * from t_user where birthday > #{min} and birthday < #{max}")
	public List<User> selectByBirthdayRange(Map<String, Object> dataMap) throws Exception;
}
