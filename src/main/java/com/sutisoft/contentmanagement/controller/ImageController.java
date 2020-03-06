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

import com.sutisoft.contentmanagement.command.ImageCommand;
import com.sutisoft.contentmanagement.services.ImageService;

@RestController
@RequestMapping("/image/")
public class ImageController {

	public static final Logger logger = LoggerFactory.getLogger(ImageController.class.getName());
	
	@Autowired
	private ImageService imageService;
	
	@PostMapping(value="/saveImage/{apiKey}",consumes="application/json")
	public ResponseEntity<String> saveImageContent(@PathVariable(value="apiKey") String apiKey,
			@RequestBody ImageCommand imageCommand) {
		
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		ResponseEntity<String> response=new ResponseEntity<>("Image Saved Successfully",HttpStatus.OK);
		try {
			Integer saveStatus= imageService.save(imageCommand, apiKey);
			if(saveStatus==null) {
				response=new ResponseEntity<String>("Image save Failed",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			logger.info("##Save Image --->"+imageCommand.getFileName());
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
				return response;
	}
	
	@PostMapping(value="/deleteImage/{apiKey}",consumes="application/json")
	public ResponseEntity<String> deleteImageContent(@PathVariable(value="apiKey") String apiKey,
			@RequestBody ImageCommand imageCommand) {
		
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		ResponseEntity<String> response=new ResponseEntity<>("Image Deleted Successfully",HttpStatus.OK);
		try {
			Integer fileDeleteStatus= imageService.delete(imageCommand, apiKey);
			if(fileDeleteStatus==null) {
				response=new ResponseEntity<String>("Image deletion Failed",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			logger.info("##Delete Image --->"+imageCommand);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
				return response;
	}
	
}
