package com.op.bootfaces.controller;

import com.op.bootfaces.model.User;
import com.op.bootfaces.persistence.UserRepository;
import com.op.bootfaces.util.UserType;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Scope(value = "session")
@Component(value = "loginController")
@ELBeanName(value = "loginController")
@Join(path = "/", to = "/login/login.jsf")
public class LoginController {

	@Autowired
	private UserRepository userRepository;

//	private PasswordEncoder passwordEncoder;

	private String username;
	private String password;
	private User loggedInUser;

	private User user = new User();
	private String currentUserId;
	private String currentRole;
	private UserType selectedUserType;
	private boolean active = true; ;
	private Long selectedCompanyId;


	public String login2(){
		User user = userRepository.findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			loggedInUser = user;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
			return "/task/task-list.xhtml?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or password", null));
			return "/login/login.xhtml?faces-redirect=true";
		}

//		return "/product/product-list.xhtml?faces-redirect=true";
	}

	public String login() {
		User user = userRepository.findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			loggedInUser = user;
			String role = user.getUserType();
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			session.setAttribute("userId", String.valueOf(user.getId()));
			session.setAttribute("companyNo", user.getCompanyNo());
			session.setAttribute("role", role);
			if(role.equals("ADMIN")){
				return "/user/user-list.xhtml?faces-redirect=true";
			}else if(role.equals("CUSTOMER")){
				return "/project/project-list.xhtml?faces-redirect=true";
			} else {
				return "/task/task-list.xhtml?faces-redirect=true";
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or password", null));
			return "/login/login.xhtml?faces-redirect=true";
		}
	}

//	public String save(){
//		System.out.println(user.getUsername());
//		System.out.println(user.getPassword());
//		User existingUser = userRepository.findByUsername(user.getUsername());
//		System.out.println(existingUser);
//		if(existingUser == null){
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username already exist.", null));
//		} else {
//			currentUserId = "1";
//			currentRole = LoginController.getUserAttribute("role");
//			if (active) {
//				user.setActiveStatus("Y");
//			} else {
//				user.setActiveStatus("N");
//			}
//			user.setUserType(String.valueOf(selectedUserType));
//			user.setCompanyNo("1");
//			userRepository.save(user);
//			user = new User();
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully saved!", null));
//		}
//		return "/login/login.xhtml?faces-redirect=true";
//	}
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "/login/login.xhtml?faces-redirect=true";
	}

	public String registerLoad(){
		return "/login/register.xhtml?faces-redirect=true";
	}

	public boolean isAdmin() {
		return loggedInUser != null && "ADMIN".equals(loggedInUser.getUserType());
	}

	public boolean isEmployee() {
		return loggedInUser != null && "EMPLOYEE".equals(loggedInUser.getUserType());
	}

	public boolean isCustomer() {
		return loggedInUser != null && "CUSTOMER".equals(loggedInUser.getUserType());
	}
	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	public static String getUserAttribute(String key) {
		HttpSession session = getSession();
		return session != null ? (String) session.getAttribute(key) : null;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
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

	public UserType getSelectedUserType() {
		return selectedUserType;
	}

	public void setSelectedUserType(UserType selectedUserType) {
		this.selectedUserType = selectedUserType;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
