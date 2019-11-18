package com.sutisoft.contentmanagement.services;

import java.util.List;

import com.sutisoft.contentmanagement.command.ContentManagementCommand;

public interface IndexService {

   public List<ContentManagementCommand> getContentList(String apiKey,String productName);
   
   public ContentManagementCommand getContent(String apiKey,String productName,String contentKey);
}
