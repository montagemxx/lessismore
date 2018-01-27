package com.mxx.it.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mxx.it.dao.UserDao;
import com.mxx.it.model.UserModel;
import com.mxx.it.pojo.User;


@Controller
public class UserController {
	
	@Autowired
//	@Qualifier("userDaoImpl")
	UserDao userDao;
	
	@Autowired
//	@Qualifier("userDaoImpl2")
	UserDao userDao2;
	
	@Value("app.name")
	String appName;
	
	@Value("${app.name.test}")
	String appNameT;
	
	String applicationName;
	
	@Autowired
	public void setApplicationName(String applicationName1) {
		this.applicationName = applicationName1;
	}

	@RequestMapping(value= "/searchUser", method=RequestMethod.POST)
	public String getUser(@RequestParam("id") String id, Model model){
		model.addAttribute("usermodel", new UserModel(userDao.getUser(id)));
//		System.out.println(model.asMap().get("usermodel"));
		return "users/user";
	}
	
	@RequestMapping(value="/searchAllUsers", method=RequestMethod.GET)
	public String getAllUsers( Model model){
		List<User> users = userDao.getAllUsers();
		List<UserModel> usersModel = new ArrayList<UserModel>();
		for(User user:users){
			usersModel.add(new UserModel(user));
		}
		System.out.println("gggggggggggggggggggggggggggggggggggggg"+appName);
		
		System.out.println("ggggggggggggggggggggggggggggggggggggggt"+appNameT);
		
		System.out.println("ggggggggggggggggggggggggggggggggggggggtrrr"+applicationName);

		model.addAttribute("usersmodel", usersModel);
		return "users/users";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(){
		return "users/add_users";
	}
	@RequestMapping(value="/addUsers",method=RequestMethod.POST)
	public ModelAndView addUsers(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("email") String email){
		
		userDao.addUsers(id, name, email);
		return new ModelAndView("redirect:searchAllUsers"); 
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(){
		return "users/del_user";
	}
	@RequestMapping(value="/deleteUser", method=RequestMethod.POST)
	public ModelAndView deleteUser(@RequestParam("id") String id){
		userDao.deleteUser(id);
		return new ModelAndView("redirect:searchAllUsers"); 
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(@RequestParam("id") String id, Model model){
		User u = userDao.getUser(id);
		model.addAttribute("id", u.getId());
		model.addAttribute("name", u.getName());
		model.addAttribute("email",u.getEmail());
		return "users/update_user";
	}
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public ModelAndView updateUser(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("email") String email){
		userDao.updateUser(id, name, email);
		return new ModelAndView("redirect:searchAllUsers");
		
	}
}
