package com.op.bootfaces.controller;

import com.op.bootfaces.model.Company;
import com.op.bootfaces.model.User;
import com.op.bootfaces.persistence.CompanyRepository;
import com.op.bootfaces.persistence.UserRepository;
import com.op.bootfaces.util.UserType;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Scope(value = "session")
@Component(value = "userController")
@ELBeanName(value = "userController")
@Join(path = "/user", to = "/user/user-form.jsf")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CompanyRepository companyRepository;

	@PostConstruct
	public void init() {
		companies = companyRepository.findAll();
	}

	private UserType selectedUserType;
	private boolean active = true; ;
	private Long selectedCompanyId;
	private List<Company> companies;

	private User user = new User();
	private String currentUserId;
	private String currentRole;

	public UserType getSelectedUserType() {
		return selectedUserType;
	}

	public void setSelectedUserType(UserType selectedUserType) {
		this.selectedUserType = selectedUserType;
	}

	public UserType[] getUserTypes() {
		return UserType.values();
	}

	public String save(){
		User existingUser = userRepository.findByUsername(user.getUsername());
		currentUserId = LoginController.getUserAttribute("userId");
		System.out.println(currentUserId);
		if(existingUser != null){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username already exist.", null));
		} else {
//			currentUserId = LoginController.getUserAttribute("userId");
			currentRole = LoginController.getUserAttribute("role");

			if (active) {
				user.setActiveStatus("Y");
			} else {
				user.setActiveStatus("N");
			}
			user.setUserType(String.valueOf(selectedUserType));
			user.setCompanyNo(String.valueOf(selectedCompanyId));
			userRepository.save(user);
			user = new User();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully saved!", null));
		}
		if(currentUserId==null){
			return "/login/login.xhtml?faces-redirect=true";
		} else {
			return "/user/user-form.jsf?faces-redirect=true";
		}
	}

	public String edit(Long id) {
		this.user = userRepository.findById(id);
		return "/user/user-form.jsf?faces-redirect=true"; // Navigate to edit user page
	}

	public User getUser() {
		return user;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getSelectedCompanyId() {
		return selectedCompanyId;
	}

	public void setSelectedCompanyId(Long selectedCompanyId) {
		this.selectedCompanyId = selectedCompanyId;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
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

	public void setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
	}
}
