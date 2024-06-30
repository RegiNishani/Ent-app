package com.op.bootfaces.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "company_name")
	private String companyName;

	@Column
	private String address;

	@Column(name = "company_size")
	private String companySize;

}
