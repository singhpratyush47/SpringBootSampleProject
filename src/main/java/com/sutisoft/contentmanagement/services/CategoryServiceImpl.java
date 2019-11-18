package com.sutisoft.contentmanagement.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sutisoft.contentmanagement.command.CategoryCommand;
import com.sutisoft.contentmanagement.domain.Category;
import com.sutisoft.contentmanagement.domain.StatusMain;
import com.sutisoft.contentmanagement.repositories.CategoryRepository;
import com.sutisoft.contentmanagement.repositories.CompanyRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	public static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class.getName());
	
	private final CategoryRepository categoryRepo;
	private final CompanyRepository companyRep;
	
	public CategoryServiceImpl(CategoryRepository categoryRepo,CompanyRepository companyRep) {
		super();
		this.categoryRepo = categoryRepo;
		this.companyRep=companyRep;
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
	@Transactional
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
	@Transactional
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

}
