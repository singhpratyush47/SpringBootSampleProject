package com.sutisoft.contentmanagement.converters;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sutisoft.contentmanagement.command.CategoryCommand;
import com.sutisoft.contentmanagement.domain.Category;
import com.sutisoft.contentmanagement.domain.StatusMain;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
	
	public static final Logger logger = LoggerFactory.getLogger(CategoryCommandToCategory.class.getName());
	
	@Override
	public Category convert(CategoryCommand source) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		if(source==null) {
			return null;
		}
		Category category=new Category();
		category.setName(source.getName());
		category.setType(source.getType());
		
		StatusMain status=new StatusMain();
		status.setStatusId(source.getStatusId());
		category.setStatus(status);
		category.setCreatedBy(source.getCreatedBy());
		category.setCreatedDate(new Date());
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return category;
	}

}
