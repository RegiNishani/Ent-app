package com.op.bootfaces.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "TASK_TITLE")
	private String taskTitle;

	@Column(name = "TASK_DESC")
	private String taskDesc;

	@Column(name = "TASK_TYPE")
	private String taskType;

	@Column(name = "TASK_OWNER")
	private String taskOwner;

	@Column(name = "TASK_TIME")
	private String taskTime;

	@Column(name = "TASK_STATUS")
	private String taskStatus;

	@Column(name = "PROJECT_ID")
	private String projectId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TASK_DATE")
	private Date taskDate;


}
