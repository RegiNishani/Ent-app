package com.op.bootfaces.controller;

import com.op.bootfaces.model.Project;
import com.op.bootfaces.model.Task;
import com.op.bootfaces.persistence.TaskRepository;
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
@Component(value = "listTasks")
@ELBeanName(value = "listTasks")
@Join(path = "/task_list", to = "/task/task-list.jsf")
public class ListTaskController {
	@Autowired
	private TaskRepository taskRepository;
	private List<Task> tasks;

	private String currentUserId;
	private String currentRole;


	@Deferred
	@RequestAction
	@IgnorePostback
	public void loadData() {
		currentUserId = LoginController.getUserAttribute("userId");
		currentRole = LoginController.getUserAttribute("role");
		if(currentRole.equals("ADMIN") || currentRole.equals("CUSTOMER")){
			tasks = taskRepository.findAll();
		} else {
			tasks = taskRepository.findByTaskOwner(currentUserId);
		}
	}

	public String delete(Task task) {
		taskRepository.delete(task.getId());
		loadData();
		return null;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
