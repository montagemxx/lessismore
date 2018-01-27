package com.mxx.it.dao;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.mxx.it.pojo.User;

public class UserDaoImplTest {
	private UserDaoImpl userDao;
	private User user;
	NamedParameterJdbcTemplate template;
	
	@Before
	public void setUp(){
		userDao = new UserDaoImpl();
		template = EasyMock.createMock(NamedParameterJdbcTemplate.class);
		ReflectionTestUtils.setField(userDao, "template", template);
		
//		user = EasyMock.createMock(User.class);
//		ReflectionTestUtils.setField(userDao, "user", user);
		
	}
	
	@Test
	public void testSetTemplate(){
		userDao.setTemplate(template);
		assertEquals(template, userDao.template);
	}
	
	@Test
	public void testGetUser(){
		String id = "1002";
		User u = new User();
		u.setId("1002");
		
		Capture<String> sqlCapture = newCapture();
		Capture<HashMap> paramCapture = newCapture();
		Capture<RowMapper> rowMapperCapture = newCapture();
		
		expect(template.queryForObject(capture(sqlCapture ), capture(paramCapture), capture(rowMapperCapture))).andReturn(u).anyTimes();
		replay(template);
		User user = userDao.getUser(id);
		
		assertNotNull(user);
		assertEquals("1002", user.getId());
		
	}
	@Test
	public void testGetUser_null(){
		String id = null;
		User user = userDao.getUser(id);
		
		assertNotNull(user);
	}
	@Test
	public void testGetUser_empty(){
		String id = "";
		User user = userDao.getUser(id);
		
		assertNotNull(user);
	}
	@Test(expected = RuntimeException.class)
	public void testGetUser_Ex(){
		String id = "1002";
		
		Capture<String> sqlCapture = newCapture();
		Capture<HashMap> paramCapture = newCapture();
		Capture<RowMapper> rowMapperCapture = newCapture();
		
		expect(template.queryForObject(capture(sqlCapture ), capture(paramCapture), capture(rowMapperCapture))).andThrow(new SQLException());
		replay(template);
		
		User user = userDao.getUser(id);
	}
	
	@Test
	public void testGetAllUsers(){
		userDao.getAllUsers();
		
	}
}
