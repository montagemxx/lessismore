package com.mxx.it.dao;

import java.util.List;

import com.mxx.it.pojo.User;

public interface UserDao {
	User getUser(String id);
	public List<User> getAllUsers();
	public boolean addUsers(String id, String name, String email);
	public void deleteUser(String id);
	public void updateUser(String id, String name, String email);
}
