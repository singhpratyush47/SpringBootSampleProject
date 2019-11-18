package com.sutisoft.contentmanagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sutisoft.contentmanagement.domain.Company;
import com.sutisoft.contentmanagement.repositories.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	public static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class.getName());
	
	private final CompanyRepository companyRepo;
	
	public CompanyServiceImpl(CompanyRepository companyRepo) {
		super();
		this.companyRepo = companyRepo;
	}

	@Override
	public Company findById(Integer companyId) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Optional<Company> optionalCompany= companyRepo.findById(companyId);
		Company company=optionalCompany.get();
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return company;
	}

	@Override
	public List<Company> findAll() {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		List<Company> listOfCompany=new ArrayList<Company>();
		listOfCompany=companyRepo.findAll();
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return listOfCompany;
	}

	@Override
	public Company save(Company company) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Company savedCompany= companyRepo.save(company);
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return savedCompany;
	}

}
