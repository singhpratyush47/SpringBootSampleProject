package com.sutisoft.contentmanagement.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sutisoft.contentmanagement.command.ContentManagementCommand;
import com.sutisoft.contentmanagement.converters.ContentManagementCommandToContentManagement;
import com.sutisoft.contentmanagement.domain.ContentManagement;
import com.sutisoft.contentmanagement.services.ContentManagementService;

@RestController
@RequestMapping("/content-management/")
public class ContentManagementController {
	
	public static final Logger logger = LoggerFactory.getLogger(ContentManagementController.class.getName());
	
	@Autowired
	private ContentManagementService contentManagementService;
	@Autowired
	private ContentManagementCommandToContentManagement contentManagementCommandToContentManagement;
	
	@PostMapping(value="/saveContent/{apiKey}",consumes="application/json")
	public ResponseEntity<String> saveContent(@PathVariable(value = "apiKey") String apiKey,
			@RequestBody ContentManagementCommand contentManagementCommand) {
		
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		String productName=null;
		String categoryName=null;
		ContentManagement contentManagementToBeSaved=null;
		ResponseEntity<String> response=new ResponseEntity<>("Contents saved successfully",HttpStatus.OK);
		try {
			contentManagementToBeSaved= contentManagementCommandToContentManagement
																	.convert(contentManagementCommand);
			productName=contentManagementCommand.getProductName();
			categoryName=contentManagementCommand.getCategoryName();
			ContentManagement savedContentManagement= contentManagementService
					.save(contentManagementToBeSaved,apiKey,productName,categoryName);
			if(savedContentManagement==null) {
				response=new ResponseEntity<String>("Contents saving failed",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return response;
	}
	
	@PostMapping(value="/updateContent/{apiKey}",consumes="application/json")
	public ResponseEntity<String> updateContent(@PathVariable(value = "apiKey") String apiKey,
			@RequestBody ContentManagementCommand contentManagementCommand) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		ResponseEntity<String> response=new ResponseEntity<>("Content updated Successfully",HttpStatus.OK);
		try {
		Integer updateStatus=	contentManagementService.update(contentManagementCommand,apiKey);
		if(updateStatus==null) {
			response=new ResponseEntity<>("Content updated Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
			response=new ResponseEntity<>("Content updated Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return response;
	}
	
}
