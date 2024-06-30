package com.op.bootfaces.controller;

import com.op.bootfaces.model.User;
import com.op.bootfaces.persistence.UserRepository;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Scope(value = "session")
@Component(value = "listUsers")
@ELBeanName(value = "listUsers")
@Join(path = "/user_list", to = "/user/user-list.jsf")
public class ListUserController {

	@Autowired
	private UserRepository userRepository;

	private List<User> users;
	private String currentUserId;
	private String currentRole;
	private String currentCompanyNo;

	@Deferred
	@RequestAction
	@IgnorePostback
	public void loadData() {
		currentUserId = LoginController.getUserAttribute("userId");
		currentRole = LoginController.getUserAttribute("role");
		currentCompanyNo = LoginController.getUserAttribute("companyNo");
		if(currentRole.equals("ADMIN")) {
			users = userRepository.findAll();
		} else{
			users = userRepository.findByCompanyNo(currentCompanyNo);
		}
	}

	public String delete(User user) {

		userRepository.delete(user.getId());
		loadData();
		return null;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}

	public String getCurrentRole() {
		return currentRole;
	}

	public String getCurrentCompanyNo() {
		return currentCompanyNo;
	}

	public void setCurrentCompanyNo(String currentCompanyNo) {
		this.currentCompanyNo = currentCompanyNo;
	}

	public void setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
	}

}
