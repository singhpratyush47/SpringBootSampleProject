package com.sutisoft.contentmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sutisoft.contentmanagement.command.ContentManagementCommand;
import com.sutisoft.contentmanagement.services.IndexService;


@RestController
@RequestMapping("/Content-Management/")
public class IndexController {

	public static final Logger logger = LoggerFactory.getLogger(IndexController.class.getName());
	
	@Autowired
	private IndexService indexService;
	
	@PostMapping(value="/getContentList",produces="application/json")
	public ResponseEntity<List<ContentManagementCommand>> getContentList(@RequestParam(name="authKey")
	String authKey,@RequestParam(name="productName") String productName) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		
		List<ContentManagementCommand> listOfContents=new ArrayList<>();
		ResponseEntity<List<ContentManagementCommand>> response=null;
		try {
			listOfContents= indexService.getContentList(authKey, productName);
			response=new ResponseEntity<List<ContentManagementCommand>>(listOfContents,HttpStatus.OK);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e);
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return response;
	}
	
	@PostMapping(value="/getContent",produces="application/json")
	public ResponseEntity<ContentManagementCommand> getContent(@RequestParam(name="authKey")
	String authKey,@RequestParam(name="productName") String productName,
	@RequestParam(name="contentKey") String contentId) {
		
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		ContentManagementCommand contentManagementCommand=null;
		ResponseEntity<ContentManagementCommand> response=null;
		try {
			logger.info("###Request with key-->"+authKey+" productName-->"+productName+" contentKey-->"+contentId);
			contentManagementCommand= indexService.getContent(authKey, productName, contentId)	;
			if(contentManagementCommand==null) {
				logger.info("###No Content Found With Above Request###");
			}else {
				logger.info("###Response content-->"+contentManagementCommand);
			}
			response=new ResponseEntity<>(contentManagementCommand,HttpStatus.OK);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e);
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return response;
	}
	
	@PostMapping(value="/search",produces="application/json")
	public ContentManagementCommand search() {
		return null;
	}
	
}
