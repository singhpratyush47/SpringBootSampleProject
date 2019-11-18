package com.sutisoft.contentmanagement.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sutisoft.contentmanagement.command.ContentManagementCommand;
import com.sutisoft.contentmanagement.domain.Category;
import com.sutisoft.contentmanagement.domain.ContentManagement;
import com.sutisoft.contentmanagement.domain.Product;
import com.sutisoft.contentmanagement.domain.StatusMain;
import com.sutisoft.contentmanagement.repositories.CategoryRepository;
import com.sutisoft.contentmanagement.repositories.CompanyRepository;
import com.sutisoft.contentmanagement.repositories.ContentManagementRepository;
import com.sutisoft.contentmanagement.repositories.ProductRepository;

@Service
public class ContentManagementServiceImpl implements ContentManagementService {

	public static final Logger logger = LoggerFactory.getLogger(ContentManagementServiceImpl.class.getName());
	
	private final ContentManagementRepository contentManagementRepo;
	private final CompanyRepository companyRepository;
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepo;
	
	public ContentManagementServiceImpl(ContentManagementRepository contentManagementRepo,
			CompanyRepository companyRepository,ProductRepository productRepository,CategoryRepository categoryRepo) {
		super();
		this.contentManagementRepo = contentManagementRepo;
		this.companyRepository=companyRepository;
		this.productRepository=productRepository;
		this.categoryRepo=categoryRepo;
	}

	@Override
	public ContentManagement findById(Integer contentManagementId) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Optional<ContentManagement> optionalContentManagement=contentManagementRepo.findById(contentManagementId);
		ContentManagement contentManagement=optionalContentManagement.get();
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return contentManagement;
	}

	@Override
	public List<ContentManagement> findAll() {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		List<ContentManagement> listOfContentManagement=new ArrayList<>();
		listOfContentManagement=contentManagementRepo.findAll();
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return listOfContentManagement;
	}

	@Override
	@Transactional
	public ContentManagement save(ContentManagement contentManagement,String apiKey,
			String productName, String categoryName) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Integer companyId=null;
		Integer productId=null;
		Integer categoryId=null;
		ContentManagement savedContentManagement=null;
		try {
			companyId= companyRepository.findByApiKey(apiKey);
			productId=productRepository.findByNameAndCompanyId(productName,companyId);
			categoryId=categoryRepo.findByNameAndCompanyId(categoryName, companyId);
			
			contentManagement.setCompanyId(companyId);
			
			Category category=new Category();
			category.setCategoryId(categoryId);
			contentManagement.setCategory(category);
			
			Product product=new Product();
			product.setProductId(productId);
			contentManagement.setProduct(product);
			
			savedContentManagement= contentManagementRepo.save(contentManagement);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return savedContentManagement;
	}

	@Override
	@Transactional
	public Integer update(ContentManagementCommand contentManagementCommand, String apiKey) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Integer companyId=null;
		String oldProduct=null;
		String oldCategory=null;
		String oldContentId=null;
		Integer contentId=null;
		Integer updatedProductId=null;
		Integer updatedCategoryId=null;
		Integer updateStatus=null;
		Integer oldStatus=null;
		try {
			companyId=companyRepository.findByApiKey(apiKey);
			oldCategory=contentManagementCommand.getOldCategoryName();
			oldProduct=contentManagementCommand.getOldProductName();
			oldContentId=contentManagementCommand.getOldContentId();
			oldStatus=contentManagementCommand.getOldStatus();
			
			contentId= contentManagementRepo.findContentByProductAndCategory(oldProduct, oldCategory, companyId, oldContentId,oldStatus);
			updatedProductId= productRepository.findByNameAndCompanyId(contentManagementCommand.getProductName(), companyId);
			updatedCategoryId= categoryRepo.findByNameAndCompanyId(contentManagementCommand.getCategoryName(), companyId);
			Optional<ContentManagement> optionalContentManagement=contentManagementRepo.findById(contentId);
			Optional<Product> optionalProduct=productRepository.findById(updatedProductId);
			Optional<Category> optionalCategory= categoryRepo.findById(updatedCategoryId);
			
			ContentManagement contentManagement=optionalContentManagement.get();
			Product product=optionalProduct.get();
			Category category=optionalCategory.get();
			
			
			contentManagement.setContentManagementId(contentId);
			contentManagement.setCategory(category);
			contentManagement.setCategoryType(contentManagementCommand.getCategoryType());
			contentManagement.setCompanyId(companyId);
			contentManagement.setContentId(contentManagementCommand.getContentId());
			contentManagement.setDescription(contentManagementCommand.getDescription());
			contentManagement.setProduct(product);
			contentManagement.setUpdatedBy(contentManagementCommand.getUpdatedBy());
			contentManagement.setUpdatedDate(new Date());
			
			StatusMain statusMain=new StatusMain();
			statusMain.setStatusId(contentManagementCommand.getStatus());
			contentManagement.setStatus(statusMain);
			contentManagementRepo.save(contentManagement);
			updateStatus=1;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return updateStatus;
	}

}
