package com.op.bootfaces.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "projects")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "project_desc")
	private String projectDesc;

	@Column(name = "company_no")
	private String companyNo;

	@Column(name = "active_status")
	private String activeStatus;

}
