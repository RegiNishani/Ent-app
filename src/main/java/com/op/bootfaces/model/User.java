package com.op.bootfaces.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String username;

	@Column
	private String password;

	@Column(name = "user_type")
	private String userType;

	@Column(name = "company_no")
	private String companyNo;
//	@ManyToOne
//	@JoinColumn(name = "company_no")
//	private Company company;

	@Column(name = "active_status")
	private String activeStatus;


}
