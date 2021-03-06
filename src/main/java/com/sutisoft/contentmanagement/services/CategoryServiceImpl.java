package com.sutisoft.contentmanagement.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sutisoft.contentmanagement.command.CategoryCommand;
import com.sutisoft.contentmanagement.converters.CategoryCommandToCategory;
import com.sutisoft.contentmanagement.domain.Category;
import com.sutisoft.contentmanagement.domain.StatusMain;
import com.sutisoft.contentmanagement.repositories.CategoryRepository;
import com.sutisoft.contentmanagement.repositories.CompanyRepository;
import com.sutisoft.contentmanagement.utils.StatusMap;

@Service
public class CategoryServiceImpl implements CategoryService {

	public static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class.getName());
	
	private final CategoryRepository categoryRepo;
	private final CompanyRepository companyRep;
	private final CategoryCommandToCategory commandToCategory;
	
	public CategoryServiceImpl(CategoryRepository categoryRepo,CompanyRepository companyRep,
			CategoryCommandToCategory commandToCategory) {
		super();
		this.categoryRepo = categoryRepo;
		this.companyRep=companyRep;
		this.commandToCategory=commandToCategory;
	}

	@Override
	public Category findById(Integer categoryId) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Optional<Category> optionalCategory=categoryRepo.findById(categoryId);
		Category category=optionalCategory.get();
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return category;
	}

	@Override
	public List<Category> findAll() {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		List<Category> listOfCategories=new ArrayList<>();
		listOfCategories=categoryRepo.findAll();
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return listOfCategories;
	}

	@Override
	public Category save(Category category,String apiKey) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Integer companyId=null;
		Category savedCategory=null;
		try {
			companyId= companyRep.findByApiKey(apiKey);
			category.setCompanyId(companyId);
			savedCategory=categoryRepo.save(category);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return savedCategory;
	}

	@Override
	public Integer update(CategoryCommand categoryCommand, String apiKey) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Integer companyId=null;
		Integer categoryIdToBeUpdated=null;
		String updatedCategoryName=null;
		String updatedCategoryType=null;
		Integer updateStatus=null;
		try {
			companyId= companyRep.findByApiKey(apiKey);
			categoryIdToBeUpdated=categoryRepo.findByNameAndCompanyId(categoryCommand.getOldName(), companyId);
			Optional<Category> optionalCategory=categoryRepo.findById(categoryIdToBeUpdated);
			Category category=optionalCategory.get();
			updatedCategoryName=categoryCommand.getName();
			updatedCategoryType=categoryCommand.getType();
			
			category.setCategoryId(categoryIdToBeUpdated);
			category.setName(updatedCategoryName);
			category.setType(updatedCategoryType);
			category.setLastModifiedDate(new Date());
			
			StatusMain statusMain=new StatusMain();
			statusMain.setStatusId(categoryCommand.getStatusId());
			category.setStatus(statusMain);
			categoryRepo.save(category);
			updateStatus=1;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return updateStatus;
	}

	@Override
	public Category saveOrUpdate(CategoryCommand categoryCommand, String apiKey) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Integer companyId=null;
		Integer categoryIdToBeUpdated=null;
		String updatedCategoryName=null;
		String updatedCategoryType=null;
		Category savedOrUpdatedCategory=null;
		try {
			companyId= companyRep.findByApiKey(apiKey);
			categoryIdToBeUpdated=categoryRepo.findByNameAndCompanyId(categoryCommand.getOldName(), companyId);
			updatedCategoryName=categoryCommand.getName();
			updatedCategoryType=categoryCommand.getType();
			Category category=null;
			
			if(categoryIdToBeUpdated==null) {
				//save new Category
				category= commandToCategory.convert(categoryCommand);
				category.setCompanyId(companyId);
			}else {
				//update existing category
				Optional<Category> optionalCategory=categoryRepo.findById(categoryIdToBeUpdated);
				category=optionalCategory.get();
				category.setCategoryId(categoryIdToBeUpdated);
				category.setName(updatedCategoryName);
				category.setType(updatedCategoryType);
				category.setLastModifiedDate(new Date());
				
				StatusMain statusMain=new StatusMain();
				statusMain.setStatusId(categoryCommand.getStatusId());
				category.setStatus(statusMain);
			}
			
			savedOrUpdatedCategory= categoryRepo.save(category);
			logger.info("##Saved Or Updated Category Is --->"+savedOrUpdatedCategory);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return savedOrUpdatedCategory;
	}

	@Override
	public Integer delete(Category category, String apiKey) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Integer companyId=null;
		Integer categoryId=null;
		String categoryName=null;
		Integer updateStatus=null;
		try {
			companyId=companyRep.findByApiKey(apiKey);
			categoryName=category.getName();
			categoryId=categoryRepo.findByNameAndCompanyId(categoryName, companyId);
			Optional<Category> optionalCategory= categoryRepo.findById(categoryId);
			Category categoryToDelete= optionalCategory.get();
			
			StatusMain statusMain=new StatusMain();
			statusMain.setStatusId(StatusMap.DELETE);
			categoryToDelete.setStatus(statusMain);
			
			categoryRepo.save(categoryToDelete);
			updateStatus=1;  
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return updateStatus;
	}

}
