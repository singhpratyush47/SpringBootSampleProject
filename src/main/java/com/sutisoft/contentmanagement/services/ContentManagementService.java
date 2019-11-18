package com.sutisoft.contentmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sutisoft.contentmanagement.command.ContentManagementCommand;
import com.sutisoft.contentmanagement.domain.ContentManagement;


public interface ContentManagementService {

	public ContentManagement findById(Integer contentManagementId);
	
	public List<ContentManagement> findAll();
	
	public ContentManagement save(ContentManagement contentManagement, String apiKey,
			String productName, String categoryName);

	public Integer update(ContentManagementCommand contentManagementCommand, String apiKey);
}
