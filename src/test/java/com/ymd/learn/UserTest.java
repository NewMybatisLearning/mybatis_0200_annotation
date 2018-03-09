package com.ymd.learn;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ymd.learn.entity.User;
import com.ymd.learn.mapper.UserMapper;

public class UserTest {
	
SqlSession session = null;
UserMapper userMapper  = null;
	
	@Before
	public void setup() {
		try {
		String resource = "mybatis-configuration.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sqlSessionFactory.openSession();
		userMapper = session.getMapper(UserMapper.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectUserById() throws Exception {
		User user = userMapper.selectUserById(1);
		System.out.println("user  = " + user);
	}
	
	@Test
	public void testSelectUserAll() throws Exception {
		List<User> users = userMapper.selectUserAll();
		users.forEach(user -> {
			System.out.println(user);
		});
	}
	
	@Test
	public void testSelectLikeUsername() throws Exception {
		List<User> users =  userMapper.SelectLikeUsername("mi");
		users.forEach(user -> {
			System.out.println(user);
		});
	}
	
	@Test
	public void testInsertUser()  throws Exception {
		User user = new User();
		user.setUsername("lincoln");
		user.setBirthday(new Date());
		user.setAddress("shenzhen songgang");
		user.setSex("1");
		userMapper.insertUser(user);
		session.commit();
	}
	
	@Test
	public void testUpdateUserById()  throws Exception {
		User user = userMapper.selectUserById(1);
		user.setUsername("michaelyao");
		userMapper.updateUserById(user);
		session.commit();
	}
	
	@Test
	public void testDeleteUserById()  throws Exception {
		userMapper.deleteUserById(7);
		session.commit();
	}
	
	@Test
	public void testSelectByBirthdayRange()  throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("min", "2018-03-01 00:00:00");
		map.put("max", "2018-03-08 00:00:00");
		
		List<User> users = userMapper.selectByBirthdayRange(map);
		users.forEach(user -> {
			System.out.println(user);
		});
	}
	
	
	
	@After
	public void destory() {
		session.close();
	}
	
}
