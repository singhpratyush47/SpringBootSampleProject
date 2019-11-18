package com.sutisoft.contentmanagement.converters;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sutisoft.contentmanagement.command.CompanyCommand;
import com.sutisoft.contentmanagement.domain.Company;
import com.sutisoft.contentmanagement.domain.StatusMain;

@Component
public class CompanyCommandToCompany implements Converter<CompanyCommand, Company> {

	@Override
	public Company convert(CompanyCommand source) {
		
		if(source==null) {
			return null;
		}
		Company company=new Company();
		company.setCompanyApiKey(source.getCompanyApiKey());
		company.setCompanyName(source.getCompanyName());
		company.setCreatedBy(source.getCreatedBy());
		company.setCreatedDate(new Date());
		company.setLastModifiedBy(source.getLastModifiedBy());
		
		StatusMain statusMain=new StatusMain();
		statusMain.setStatusId(source.getStatus());
		company.setStatus(statusMain);
		return company;
	}

}
