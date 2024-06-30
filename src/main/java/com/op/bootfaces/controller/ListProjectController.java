package com.op.bootfaces.controller;

import com.op.bootfaces.model.Project;
import com.op.bootfaces.model.User;
import com.op.bootfaces.persistence.ProjectRepository;
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
@Component(value = "listProjects")
@ELBeanName(value = "listProjects")
@Join(path = "/project_list", to = "/project/project-list.jsf")
public class ListProjectController {

	@Autowired
	private ProjectRepository projectRepository;
	private List<Project> projects;
	private String currentUserId;
	private String currentCompanyId;
	private String currentRole;
	@Deferred
	@RequestAction
	@IgnorePostback
	public void loadData() {
		currentUserId = LoginController.getUserAttribute("userId");
		currentCompanyId = LoginController.getUserAttribute("companyNo");
		currentRole = LoginController.getUserAttribute("role");
		if(currentRole.equals("ADMIN")) {
			projects = projectRepository.findAll();
		} else {
			projects = projectRepository.findByCompanyNo(currentCompanyId);
		}
	}

	public String delete(Project project) {
		projectRepository.delete(project.getId());
		loadData();
		return null;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
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

	public String getCurrentCompanyId() {
		return currentCompanyId;
	}

	public void setCurrentCompanyId(String currentCompanyId) {
		this.currentCompanyId = currentCompanyId;
	}
}
