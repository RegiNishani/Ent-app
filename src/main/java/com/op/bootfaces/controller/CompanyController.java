package com.op.bootfaces.controller;

import com.op.bootfaces.model.Company;
import com.op.bootfaces.model.User;
import com.op.bootfaces.persistence.CompanyRepository;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Scope(value = "session")
@Component(value = "companyController")
@ELBeanName(value = "companyController")
@Join(path = "/company", to = "/company/company-form.jsf")
public class CompanyController {
	@Autowired
	private CompanyRepository companyRepository;

	private Company company = new Company();
	private String currentUserId;
	private String currentRole;

	public String save(){
		currentUserId = LoginController.getUserAttribute("userId");
		currentRole = LoginController.getUserAttribute("role");

		companyRepository.save(company);
		company = new Company();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully saved!", null));

		return "/company/company-form.xhtml?faces-redirect=true";
	}

	public String edit(Long id) {
		this.company = companyRepository.findById(id);
		return "/company/company-form.jsf?faces-redirect=true"; // Navigate to edit user page
	}

	public Company getCompany() {
		return company;
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
}
