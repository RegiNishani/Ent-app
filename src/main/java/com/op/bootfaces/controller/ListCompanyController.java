package com.op.bootfaces.controller;

import com.op.bootfaces.model.Company;
import com.op.bootfaces.persistence.CompanyRepository;
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
@Component(value = "listCompanies")
@ELBeanName(value = "listCompanies")
@Join(path = "/company_list", to = "/company/company-list.jsf")
public class ListCompanyController {
	@Autowired
	private CompanyRepository companyRepository;

	private List<Company> companies;

	@Deferred
	@RequestAction
	@IgnorePostback
	public void loadData(){
		companies = companyRepository.findAll();
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public String delete(Company company){
		companyRepository.delete(company.getId());
		loadData();
		return null;
	}
}
