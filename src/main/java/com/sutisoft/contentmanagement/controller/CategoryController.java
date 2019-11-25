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

import com.sutisoft.contentmanagement.command.CategoryCommand;
import com.sutisoft.contentmanagement.converters.CategoryCommandToCategory;
import com.sutisoft.contentmanagement.domain.Category;
import com.sutisoft.contentmanagement.services.CategoryService;

@RestController
@RequestMapping("/category/")
public class CategoryController {

	public static final Logger logger = LoggerFactory.getLogger(CategoryController.class.getName());
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryCommandToCategory categoryCommandToCategory;
	
	@PostMapping(value="/saveCategory/{apiKey}",consumes="application/json")
	public ResponseEntity<String> saveCategory(@PathVariable(value="apiKey") String apiKey,
			@RequestBody CategoryCommand categoryCommand) {
		
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		ResponseEntity<String> response=new ResponseEntity<>("Category Saved Successfully",HttpStatus.OK);
		try {
			Category categoryToBeSaved= categoryCommandToCategory.convert(categoryCommand);
			Category savedCategory= categoryService.save(categoryToBeSaved,apiKey);
			if(savedCategory==null) {
				response=new ResponseEntity<>("Category not saved Successfully",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return response;
	}

	@PostMapping(value="/updateCategory/{apiKey}",consumes="application/json")
	public ResponseEntity<String> updateCategory(@PathVariable(value="apiKey") String apiKey,
			@RequestBody CategoryCommand categoryCommand) {
		
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		ResponseEntity<String> response=new ResponseEntity<>("Category saved or Updated Successfully",HttpStatus.OK);
		try {
			Category savedOrUpdatedCategory= categoryService.saveOrUpdate(categoryCommand,apiKey);
			if(savedOrUpdatedCategory==null) {
				response=new ResponseEntity<>("Failed To Save or Update Category",HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return response;
	}
	
	@PostMapping(value="/deleteCategory/{apiKey}",consumes="application/json")
	public ResponseEntity<String> deleteCategory(@PathVariable(value="apiKey") String apiKey,
			@RequestBody CategoryCommand categoryCommand){
		
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		ResponseEntity<String> response=new ResponseEntity<>("Category deleted Successfully",HttpStatus.OK);
		try {
			Category categoryToBeDeleted= categoryCommandToCategory.convert(categoryCommand);
			Integer deletedStatus= categoryService.delete(categoryToBeDeleted, apiKey);
			if(deletedStatus==null) {
				response=new ResponseEntity<>("Category Not Found To Deletion",HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return response;
	}
	
}
