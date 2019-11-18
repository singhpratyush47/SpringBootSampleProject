package com.sutisoft.contentmanagement.converters;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sutisoft.contentmanagement.command.ContentManagementCommand;
import com.sutisoft.contentmanagement.domain.ContentManagement;
import com.sutisoft.contentmanagement.domain.StatusMain;

@Component
public class ContentManagementCommandToContentManagement implements 
					Converter<ContentManagementCommand, ContentManagement> {

	public static final Logger logger = LoggerFactory.getLogger(ContentManagementCommandToContentManagement.class.getName());
	
	@Override
	public ContentManagement convert(ContentManagementCommand source) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		if(source==null) {
			return null;
		}
		ContentManagement contentManagement=new ContentManagement();
		contentManagement.setCategoryType(source.getCategoryType());
		contentManagement.setContentId(source.getContentId());
		contentManagement.setUpdatedBy(source.getUpdatedBy());
		contentManagement.setUpdatedDate(new Date());
		contentManagement.setDescription(source.getDescription());
		contentManagement.setModifiedBy(source.getModifiedBy());
		
		StatusMain status=new StatusMain();
		status.setStatusId(source.getStatus());
		contentManagement.setStatus(status);
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return contentManagement;
	}

}
