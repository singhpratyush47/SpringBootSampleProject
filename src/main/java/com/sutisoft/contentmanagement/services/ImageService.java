package com.sutisoft.contentmanagement.services;

import com.sutisoft.contentmanagement.command.ImageCommand;

public interface ImageService {

	public Integer save(ImageCommand command,String apiKey);
	
	public Integer delete(ImageCommand command,String apiKey);
}
