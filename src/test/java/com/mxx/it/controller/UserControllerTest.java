package com.mxx.it.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.easymock.Capture;

import static org.easymock.EasyMock.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.mxx.it.dao.UserDaoImpl;
import com.mxx.it.model.UserModel;
import com.mxx.it.pojo.User;

public class UserControllerTest {
	private UserController userController;
	private UserDaoImpl userDao;
	
	
	@Before
	public void setUp(){
		userController = new UserController();
		userDao = createMock(UserDaoImpl.class);
		ReflectionTestUtils.setField(userController, "userDao", userDao);
		
	}
	
	@Test
	public void testSetApplicationName(){
		userController.setApplicationName("ddd");
		assertEquals("ddd", userController.applicationName);
	}
	
	@Test
	public void testGetUser(){
		String id ="1002";
		Model model = new ExtendedModelMap();
		
		User u = new User();
		u.setId(id);
		
		Capture<String> idCapture = newCapture();
		expect(userDao.getUser(capture(idCapture))).andReturn(u);
		replay(userDao);

		String jspName = userController.getUser(id, model);
		
		assertEquals(id, idCapture.getValue());
		assertEquals("users/user", jspName);
		assertTrue(model.containsAttribute("usermodel"));
		assertEquals(id, ((UserModel)model.asMap().get("usermodel")).getId());

	}
	
	@Test(expected=NullPointerException.class)
	public void testGetUser_nullUser(){
		String id ="1002";
		Model model = new ExtendedModelMap();
		
		Capture<String> idCapture = newCapture();
		expect(userDao.getUser(capture(idCapture))).andReturn(null);
		
		replay(userDao);

		String jspName = userController.getUser(id, model);
		
		assertEquals(id, idCapture.getValue());
		assertEquals("users/user", jspName);
		assertTrue(model.containsAttribute("usermodel"));
		
	}
	
	@Test
	public void testGetAllUsers(){
		Model model = new ExtendedModelMap();
		
		expect(userDao.getAllUsers()).andReturn(new ArrayList<User>()).anyTimes();
		replay(userDao);
		
		String jspName = userController.getAllUsers(model);
		
		assertEquals("users/users",jspName);
		assertTrue(model.containsAttribute("usersmodel"));
	}
}
