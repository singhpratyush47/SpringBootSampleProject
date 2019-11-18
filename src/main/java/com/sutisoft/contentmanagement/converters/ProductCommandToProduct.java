package com.sutisoft.contentmanagement.converters;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sutisoft.contentmanagement.command.ProductCommand;
import com.sutisoft.contentmanagement.domain.Product;
import com.sutisoft.contentmanagement.domain.StatusMain;

@Component
public class ProductCommandToProduct implements Converter<ProductCommand, Product> {

	public static final Logger logger = LoggerFactory.getLogger(ProductCommandToProduct.class.getName());
	
	@Override
	public Product convert(ProductCommand source) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		if(source==null) {
			return null;
		}
		Product product=new Product();
		product.setDescription(source.getDescription());
		product.setName(source.getProductName());
		
		StatusMain status=new StatusMain();
		status.setStatusId(source.getStatusId());
		product.setCreatedDate(new Date());
		product.setStatus(status);
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return product;
	}

}
