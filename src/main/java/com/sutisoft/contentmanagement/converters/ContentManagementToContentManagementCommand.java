package com.sutisoft.contentmanagement.converters;

import org.springframework.stereotype.Component;

import com.sutisoft.contentmanagement.command.ContentManagementCommand;
import com.sutisoft.contentmanagement.domain.Category;
import com.sutisoft.contentmanagement.domain.ContentManagement;
import com.sutisoft.contentmanagement.domain.Product;

@Component
public class ContentManagementToContentManagementCommand  {

	public static ContentManagementCommand convertForList(ContentManagement source) {
		if(source==null) {
			return null;
		}
		ContentManagementCommand contentManagementCommand=new ContentManagementCommand();
		Category category=source.getCategory();
		Product product=source.getProduct();
		
		contentManagementCommand.setContentId(source.getContentId());
		if(category!=null) {
			contentManagementCommand.setCategoryName(category.getName());
		}
		if(product!=null) {
			contentManagementCommand.setProductName(product.getName());
		}
		contentManagementCommand.setCategoryType(source.getCategoryType());
		contentManagementCommand.setUpdatedBy(source.getUpdatedBy());
		return contentManagementCommand;
	}

	public ContentManagementCommand convert(ContentManagement source) {
		if(source==null) {
			return null;
		}
		ContentManagementCommand contentManagementCommand=new ContentManagementCommand();
		contentManagementCommand.setContentId(source.getContentId());
		if(source.getDescription()!=null && !source.getDescription().isEmpty()) {
			contentManagementCommand.setDescription(source.getDescription());
		}
		return contentManagementCommand;
	}
	
}
