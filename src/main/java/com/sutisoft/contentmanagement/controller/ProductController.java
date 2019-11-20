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

import com.sutisoft.contentmanagement.command.ProductCommand;
import com.sutisoft.contentmanagement.converters.ProductCommandToProduct;
import com.sutisoft.contentmanagement.domain.Product;
import com.sutisoft.contentmanagement.services.ProductService;

@RestController
@RequestMapping("/products/")
public class ProductController {

	public static final Logger logger = LoggerFactory.getLogger(ProductController.class.getName());
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCommandToProduct productCommandToProductController;
	
	@PostMapping(value="/saveProducts/{apiKey}",consumes="application/json")
	public ResponseEntity<String> saveProducts(@PathVariable(value="apiKey") String apiKey,
			@RequestBody ProductCommand productCommand) {
		
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		ResponseEntity<String> response=new ResponseEntity<>("Product Saved Successfully",HttpStatus.OK);
		try {
			Product productToBeSaved=productCommandToProductController.convert(productCommand);
			Product savedProduct= productService.save(productToBeSaved,apiKey);
			if(savedProduct==null) {
				response=new ResponseEntity<>("Product not saved Successfully",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return response;
	}

	@PostMapping(value="/updateProducts/{apiKey}",consumes="application/json")
	public ResponseEntity<String> updateProducts(@PathVariable(value="apiKey") String apiKey,
			@RequestBody ProductCommand productCommand) {
		
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		ResponseEntity<String> response=new ResponseEntity<>("Product Updated Successfully",HttpStatus.OK);
		try {
			Product savedOrUpdatedProduct=productService.saveOrUpdate(productCommand,apiKey);
			if(savedOrUpdatedProduct==null) {
				response=new ResponseEntity<>("Failed To Save Or Update Product",HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return response;
	}

	@PostMapping(value="/deleteProducts/{apiKey}",consumes="application/json")
	public ResponseEntity<String> deleteProduct(@PathVariable(value="apiKey") String apiKey,
			@RequestBody ProductCommand productCommand) {

		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		ResponseEntity<String> response=new ResponseEntity<>("Product Deleted Successfully",HttpStatus.OK);
		try {
			Product product= productCommandToProductController.convert(productCommand);
			Integer deletedStatus= productService.delete(product, apiKey);
			if(deletedStatus==null) {
				response=new ResponseEntity<>("Product id Not Found To Deletion",HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return response;
	}
	
}
