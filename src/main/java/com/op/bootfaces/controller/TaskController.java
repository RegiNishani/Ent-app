package com.op.bootfaces.controller;

import com.op.bootfaces.model.Company;
import com.op.bootfaces.model.Project;
import com.op.bootfaces.model.Task;
import com.op.bootfaces.model.User;
import com.op.bootfaces.persistence.ProjectRepository;
import com.op.bootfaces.persistence.TaskRepository;
import com.op.bootfaces.persistence.UserRepository;
import com.op.bootfaces.util.TaskStatus;
import com.op.bootfaces.util.TaskType;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Scope(value = "session")
@Component(value = "taskController")
@ELBeanName(value = "taskController")
@Join(path = "/task", to = "/task/task-form.jsf")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {
		users = userRepository.findByUserType("EMPLOYEE");
		projects = projectRepository.findAll();
		createdDate = new Date();
	}
	private TaskType selectedTaskType;
	private Date createdDate;
	private TaskStatus selectedTaskStatus;
	private Long selectedProjectId;
	private Long selectedUserId;
	private Task task = new Task();
	private List<Project> projects;
	private List<User> users;
	private String currentUserId;
	private String currentRole;

	public String save(){
		currentUserId = LoginController.getUserAttribute("userId");
		currentRole = LoginController.getUserAttribute("role");
		task.setTaskType(String.valueOf(selectedTaskType));
		task.setProjectId(String.valueOf(selectedProjectId));
		task.setTaskStatus(String.valueOf(selectedTaskStatus));
		task.setTaskOwner(String.valueOf(selectedUserId));
		System.out.println(createdDate);
		task.setTaskDate(new Date());
		taskRepository.save(task);
		task = new Task();
		return "/task/task-form.xhtml?faces-redirect=true";
	}

	public String edit(Long id) {
		this.task = taskRepository.findById(id);
		return "/task/task-form.jsf?faces-redirect=true"; // Navigate to edit user page
	}

	public Task getTask() {
		return task;
	}

	public TaskType getSelectedTaskType() {
		return selectedTaskType;
	}

	public void setSelectedTaskType(TaskType selectedTaskType) {
		this.selectedTaskType = selectedTaskType;
	}

	public TaskType [] getTaskTypes(){
		return TaskType.values();
	}

	public TaskStatus [] getTaskStatusList(){
		return TaskStatus.values();
	}

	public TaskStatus getSelectedTaskStatus() {
		return selectedTaskStatus;
	}

	public void setSelectedTaskStatus(TaskStatus selectedTaskStatus) {
		this.selectedTaskStatus = selectedTaskStatus;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
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

	public Long getSelectedProjectId() {
		return selectedProjectId;
	}

	public void setSelectedProjectId(Long selectedProjectId) {
		this.selectedProjectId = selectedProjectId;
	}

	public void setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
	}

	public Long getSelectedUserId() {
		return selectedUserId;
	}

	public void setSelectedUserId(Long selectedUserId) {
		this.selectedUserId = selectedUserId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
