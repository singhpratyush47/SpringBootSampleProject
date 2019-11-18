package com.sutisoft.contentmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sutisoft.contentmanagement.command.ProductCommand;
import com.sutisoft.contentmanagement.domain.Product;


public interface ProductService {

	public Product findById(Integer productId);
	
	public List<Product> findAll();
	
	public Product save(Product product,String companyApiKey);

	public Integer update(ProductCommand productCommand, String apiKey);

	public Integer delete(Product product,String companyApiKey);
}
