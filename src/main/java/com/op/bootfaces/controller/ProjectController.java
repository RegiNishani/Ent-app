package com.op.bootfaces.controller;

import com.op.bootfaces.model.Project;
import com.op.bootfaces.persistence.ProjectRepository;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "projectController")
@ELBeanName(value = "projectController")
@Join(path = "/project", to = "/project/project-form.jsf")
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;

	private Project project = new Project();
	private boolean active = true;
	private String currentUserId;
	private String currentRole;
	private String currentCompanyNo;

	public String save(){
		currentUserId = LoginController.getUserAttribute("userId");
		currentRole = LoginController.getUserAttribute("role");
		currentCompanyNo = LoginController.getUserAttribute("companyNo");
		project.setCompanyNo(currentCompanyNo);
		System.out.println("Active status: " + active);
		if(active){
			project.setActiveStatus("Y");
		} else {
			project.setActiveStatus("N");
		}
		projectRepository.save(project);
		project = new Project();
		return "/project/project-form.xhtml?faces-redirect=true";
	}

	public String edit(Long id) {
		this.project = projectRepository.findById(id);
		return "/project/project-form.jsf?faces-redirect=true"; // Navigate to edit user page
	}

	public Project getProject() {
		return project;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
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

	public String getCurrentCompanyNo() {
		return currentCompanyNo;
	}

	public void setCurrentCompanyNo(String currentCompanyNo) {
		this.currentCompanyNo = currentCompanyNo;
	}
}
