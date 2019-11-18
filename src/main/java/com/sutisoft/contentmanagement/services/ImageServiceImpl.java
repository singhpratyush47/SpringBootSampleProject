package com.sutisoft.contentmanagement.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sutisoft.contentmanagement.command.ImageCommand;

@Service
public class ImageServiceImpl implements ImageService {

	public static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class.getName());
	
	@Value("${image.save.location}")
	private String imagePathLocation;
	@Value("${contentManagement.folder.name}")
	private String contentManagementFolderName;
	
	@Override
	public Integer save(ImageCommand command, String apiKey) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		String folder=null;
		String subfolder=null;
		String filePath=null;
		String fileName=null;
		Integer imageSaveStatus=null;
		try {
			folder=command.getFolder();
			subfolder=command.getSubfolder();
			fileName=command.getFileName();
			filePath = imagePathLocation + contentManagementFolderName+"/" + folder+"/"+subfolder;
			 File fileLocFile=new File(filePath);
	            if(!fileLocFile.exists()){
	                fileLocFile.mkdirs();
	            }
			byte[] imageByteArray = decodeImage(command.getBase64String());
			FileOutputStream imageOutFile = new FileOutputStream(fileLocFile+"/"+fileName);
			imageOutFile.write(imageByteArray);
			imageOutFile.close();
			imageSaveStatus=1;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return imageSaveStatus;
	}
	
	@Override
	public Integer delete(ImageCommand command, String apiKey) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		String folder=null;
		String subfolder=null;
		String filePath=null;
		String fileName=null;
		Integer imageDeleteStatus=null;
		try {
			folder=command.getFolder();
			subfolder=command.getSubfolder();
			fileName=command.getFileName();
			filePath = imagePathLocation + contentManagementFolderName+"/" + folder+"/"+subfolder;
			File file = new File(filePath+"/"+fileName);
			if(file.delete()){
	            logger.info("File with name/path --->"+file+" deleted");
	        }else logger.info("File with name/path --->"+file+" doesn't exists");
		 file.delete();
		 imageDeleteStatus=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return imageDeleteStatus;
	}
	public static String encodeImage(byte[] imageByteArray) {
	    return Base64.encodeBase64URLSafeString(imageByteArray);
	}
	 public static byte[] decodeImage(String imageDataString) {
	        return Base64.decodeBase64(imageDataString);
	    }

}
