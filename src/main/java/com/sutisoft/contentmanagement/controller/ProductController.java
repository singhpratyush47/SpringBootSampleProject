package com.sutisoft.contentmanagement.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sutisoft.contentmanagement.command.ProductCommand;
import com.sutisoft.contentmanagement.configuration.ApplicationUserRole;
import com.sutisoft.contentmanagement.converters.ProductCommandToProduct;
import com.sutisoft.contentmanagement.domain.Product;
import com.sutisoft.contentmanagement.services.ProductService;

@Controller
@RequestMapping("/products/")
public class ProductController implements WebMvcConfigurer {

	public static final Logger logger = LoggerFactory.getLogger(ProductController.class.getName());
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCommandToProduct productCommandToProductConverter;
	
	@PostMapping(value="/saveProducts/{apiKey}",consumes="application/json")
	@ResponseBody
	public ResponseEntity<String> saveProducts(@PathVariable(value="apiKey") String apiKey,
			@RequestBody ProductCommand productCommand) {
		
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		ResponseEntity<String> response=new ResponseEntity<>("Product Saved Successfully",HttpStatus.OK);
		try {
			Product productToBeSaved=productCommandToProductConverter.convert(productCommand);
			Product savedProduct= productService.save(productToBeSaved,apiKey);
			if(savedProduct==null) {
				response=new ResponseEntity<>("Product not saved Successfully",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			logger.info("##Saved Product--> "+savedProduct);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return response;
	}

	@PostMapping(value="/updateProducts/{apiKey}",consumes="application/json")
	@ResponseBody
	public ResponseEntity<String> updateProducts(@PathVariable(value="apiKey") String apiKey,
			@RequestBody ProductCommand productCommand) {
		
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		ResponseEntity<String> response=new ResponseEntity<>("Product saved or Updated Successfully",HttpStatus.OK);
		try {
			Product savedOrUpdatedProduct=productService.saveOrUpdate(productCommand,apiKey);
			if(savedOrUpdatedProduct==null) {
				response=new ResponseEntity<>("Failed To Save Or Update Product",HttpStatus.NOT_FOUND);
			}
			logger.info("##SaveOrUpdate Product--> "+savedOrUpdatedProduct);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return response;
	}

	@PostMapping(value="/deleteProducts/{apiKey}",consumes="application/json")
	@ResponseBody
	public ResponseEntity<String> deleteProduct(@PathVariable(value="apiKey") String apiKey,
			@RequestBody ProductCommand productCommand) {

		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		ResponseEntity<String> response=new ResponseEntity<>("Product Deleted Successfully",HttpStatus.OK);
		try {
			Product product= productCommandToProductConverter.convert(productCommand);
			Integer deletedStatus= productService.delete(product, apiKey);
			if(deletedStatus==null) {
				response=new ResponseEntity<>("Product id Not Found To Deletion",HttpStatus.NOT_FOUND);
			}
			logger.info("##Delete Product--> "+product);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return response;
	}
	
	@GetMapping("/viewProducts")
	public String viewProducts(@ModelAttribute ProductCommand productCommand,Model model) {
		model.addAttribute("productCommand",productCommand);
		model.addAttribute("products",productService.findAll());
		return "products/viewProducts";
	}

	@PostMapping("/viewProducts")
	public String viewProducts(@Valid @ModelAttribute ProductCommand productCommand,
			BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println("****ERROR IN FORM*****");
			return "products/viewProducts";
		}
		model.addAttribute("productCommand",productCommand);
		if(productCommand.getProductName()!=null && !productCommand.getProductName().isEmpty()) {
			String productName=productCommand.getProductName();
			model.addAttribute("products",productService.findByName(productName));
		}else {
			model.addAttribute("products",productService.findAll());
		}
		return "products/viewProducts";
	}
	
	@GetMapping("/createProduct")
	public String createProduct(@ModelAttribute ProductCommand productCommand,Model model) {
		model.addAttribute("productCommand", productCommand);
		return "products/createProduct";
	}
	
	@PostMapping("/createProduct")
	public String validateProduct(@Valid ProductCommand productCommand,BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println("****ERROR IN FORM*****");
			return "redirect:/products/viewProducts";
		}
		Product product= productCommandToProductConverter.convert(productCommand);
		productService.save(product, "D7BxU9KMYHDeD7hriM2WHjKx1oDNVyhg");
		return "redirect:/products/viewProducts";
	}
	
}
