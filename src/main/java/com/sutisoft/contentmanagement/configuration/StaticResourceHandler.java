package com.sutisoft.contentmanagement.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
public class StaticResourceHandler implements WebMvcConfigurer{

	@Value("${image.save.location}")
	private String imagePathLocation;
	
	@Value("${logging.file.path}")
	private String logFileLocation;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String os= System.getProperty("os.name");
		if(os!=null && os.toLowerCase().contains("window")) {
			//expose images from external path
			registry
			.addResourceHandler("/files/**")
			.addResourceLocations("file:///"+imagePathLocation);
			
			//expose log from external path
			registry
	        .addResourceHandler("/logs/**") 
	        .addResourceLocations("file:///"+logFileLocation);
		}else {
			registry
			.addResourceHandler("/files/**")
			.addResourceLocations("file:"+imagePathLocation);

			//expose log from external path
			registry
	        .addResourceHandler("/logs/**") 
	        .addResourceLocations("file:"+logFileLocation);
		}
		
	 }
}
