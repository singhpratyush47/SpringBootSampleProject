package com.sutisoft.contentmanagement.services;

import java.util.List;

import com.sutisoft.contentmanagement.domain.Company;

public interface CompanyService {


	public Company findById(Integer companyId);
	
	public List<Company> findAll();
	
	public Company save(Company company);

}
