package com.sutisoft.contentmanagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sutisoft.contentmanagement.command.ContentManagementCommand;
import com.sutisoft.contentmanagement.converters.ContentManagementToContentManagementCommand;
import com.sutisoft.contentmanagement.domain.ContentManagement;
import com.sutisoft.contentmanagement.repositories.CompanyRepository;
import com.sutisoft.contentmanagement.repositories.ContentManagementRepository;
import com.sutisoft.contentmanagement.utils.StatusMap;

@Service
public class IndexServiceImpl implements IndexService {

	public static final Logger logger = LoggerFactory.getLogger(ContentManagementServiceImpl.class.getName());
	
	private final ContentManagementRepository contentManagementRepo;
	private final CompanyRepository companyRepository;
	private final ContentManagementToContentManagementCommand commandConverter;
	
	public IndexServiceImpl(ContentManagementRepository contentManagementRepo, CompanyRepository companyRepository,
			ContentManagementToContentManagementCommand commandConverter) {
		super();
		this.contentManagementRepo = contentManagementRepo;
		this.companyRepository = companyRepository;
		this.commandConverter = commandConverter;
	}

	@Override
	public List<ContentManagementCommand> getContentList(String apiKey, String productName) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Integer companyId=null;
		List<ContentManagement> listOfContents=new ArrayList<>();
		List<ContentManagementCommand> finalListOfContents=new ArrayList<>();
		try {
			companyId= companyRepository.findByApiKey(apiKey);
			listOfContents= contentManagementRepo.findByProductAndCompanyId(productName, companyId,StatusMap.ACTIVE);
			finalListOfContents=listOfContents
												.stream()
												.map(ContentManagementToContentManagementCommand::convertForList)
												.collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return finalListOfContents;
	}

	@Override
	public ContentManagementCommand getContent(String apiKey, String productName, String contentKey) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Integer companyId=null;
		ContentManagement contentManagement=null;
		ContentManagementCommand contentManagementCommand=null;
		try {
			companyId=companyRepository.findByApiKey(apiKey);
			contentManagement=contentManagementRepo.findByProductAndContentIdAndCompanyId(productName,
					contentKey, companyId,StatusMap.ACTIVE);
			contentManagementCommand=commandConverter.convert(contentManagement);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return contentManagementCommand;
	}

}
