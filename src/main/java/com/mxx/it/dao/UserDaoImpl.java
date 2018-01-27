package com.mxx.it.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mxx.it.pojo.User;

@Repository
public class UserDaoImpl implements UserDao{

	NamedParameterJdbcTemplate template;
	@Autowired
	public void setTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
		this.template = namedParameterJdbcTemplate;
	}
	
	
	public User getUser(String id) {
		User u = null;
		try{
			if(id != null && id != ""){
				String sql = "SELECT * FROM users WHERE id=:userid";
				Map<String, String> paramMap = new HashMap<>();
				paramMap.put("userid", id);
				RowMapper<User> rowMapper = new RowMapper<User>(){
					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User();
						user.setId(rs.getString(1));
						user.setName(rs.getString(2));
						user.setEmail(rs.getString(3));
						return user;
					}
				};
				u = template.queryForObject(sql, paramMap, rowMapper);
			}
			
		}catch(Exception ex){
			throw new RuntimeException("sql ex");
		}
		if(u == null){
			u = new User();
		}
		return u;
	}
	
	
	
	
	public List<User> getAllUsers(){
		String sql="SELECT * FROM users";
		List<User> users = new ArrayList<>();
		
		RowMapper<User> rowMapper = new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				return user;
			}
		};
		users = template.query(sql, rowMapper);
		
		return users;
	}
	
	public boolean addUsers(String id, String name, String email){
		String sql="INSERT INTO users(id, name, email) VALUES(:uid, :uname, :uemail)";
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("uid",id );
		paramMap.put("uname", name);
		paramMap.put("uemail", email);
		if(template.update(sql, paramMap)==3){
			return true;
		}
		return false;
		
//		String sql = "INSERT INTO users("
//		KeyHolder key = new GeneratedKeyHolder();
		
	}

	@Override
	public void deleteUser(String id) {
		String sql="DELETE FROM users WHERE id =:uid";
		Map<String, String> paramMap= new HashMap<>();
		paramMap.put("uid", id);
		template.update(sql, paramMap); 
	}

	@Override
	public void updateUser(String id, String name, String email) {
		String sql = "UPDATE users SET name = :uname, email=:uemail WHERE id=:uid";
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("uid",id );
		paramMap.put("uname", name);
		paramMap.put("uemail", email);
		template.update(sql, paramMap);
		
	}
}
