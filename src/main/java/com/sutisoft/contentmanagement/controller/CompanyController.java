package com.sutisoft.contentmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sutisoft.contentmanagement.command.CompanyCommand;
import com.sutisoft.contentmanagement.converters.CompanyCommandToCompany;
import com.sutisoft.contentmanagement.domain.Company;
import com.sutisoft.contentmanagement.services.CompanyService;

@RestController
@RequestMapping("/company/")
public class CompanyController {

	public static final Logger logger = LoggerFactory.getLogger(CompanyController.class.getName());

	@Autowired
	private CompanyCommandToCompany companyConverter;
	@Autowired
	private CompanyService companyService;
	
	@PostMapping(value="/createCompany",consumes="application/json")
	public ResponseEntity<String> createCompany(@RequestBody CompanyCommand companyCommand) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		ResponseEntity<String> response=new ResponseEntity<String>("Company Created SuccessFully",HttpStatus.OK);
		Company company=null;
		try {
			company= companyConverter.convert(companyCommand);
			Company savedCompany= companyService.save(company);
			if(savedCompany==null) {
				response=new ResponseEntity<String>("Company Creation Failed",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			logger.info("##Create Company--> "+savedCompany);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return response;
	}
}
