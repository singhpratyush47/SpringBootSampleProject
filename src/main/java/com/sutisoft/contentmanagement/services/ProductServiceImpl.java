package com.sutisoft.contentmanagement.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sutisoft.contentmanagement.command.ProductCommand;
import com.sutisoft.contentmanagement.converters.ProductCommandToProduct;
import com.sutisoft.contentmanagement.domain.Product;
import com.sutisoft.contentmanagement.domain.StatusMain;
import com.sutisoft.contentmanagement.repositories.CompanyRepository;
import com.sutisoft.contentmanagement.repositories.ProductRepository;
import com.sutisoft.contentmanagement.utils.StatusMap;

@Service
public class ProductServiceImpl implements ProductService {
	
	public static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class.getName());
	
	private final ProductRepository productRepo;
	private final CompanyRepository companyRepo;
	private final ProductCommandToProduct commandToProduct;
	
	public ProductServiceImpl(ProductRepository productRepo,CompanyRepository companyRepo,
			ProductCommandToProduct commandToProduct) {
		super();
		this.productRepo = productRepo;
		this.companyRepo = companyRepo;
		this.commandToProduct=commandToProduct;
	}

	@Override
	public Product findById(Integer productId) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Optional<Product> optionalProduct= productRepo.findById(productId);
		Product product= optionalProduct.get();
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return product;
	}

	@Override
	public List<Product> findAll() {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		List<Product> listOfProducts=new ArrayList<>();
		listOfProducts= productRepo.findAll();
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return listOfProducts;
	}

	@Override
	public Product save(Product product,String apiKey) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Integer companyId=null;
		Product savedProduct=null;
		try {
			companyId=companyRepo.findByApiKey(apiKey);
			product.setCompanyId(companyId);
			savedProduct=productRepo.save(product);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return savedProduct;
	}

	@Override
	public Integer update(ProductCommand productCommand, String apiKey) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Integer companyId=null;
		Integer productIdToUpdate=null;
		String updateProductName=null;
		String updatedProductDescription=null;
		Integer updatedStatus=null;
		Integer updateStatus=null;
		try {
			companyId=companyRepo.findByApiKey(apiKey);
			productIdToUpdate=productRepo.findByNameAndCompanyId(productCommand.getOldProductName(), companyId);
			updateProductName=productCommand.getProductName();
			updatedProductDescription=productCommand.getDescription();
			updatedStatus=productCommand.getStatusId();
			
			Optional<Product> optionalProduct=productRepo.findById(productIdToUpdate);
			Product product=optionalProduct.get();
			product.setProductId(productIdToUpdate);
			product.setName(updateProductName);
			product.setDescription(updatedProductDescription);
			product.setUpdatedDate(new Date());
			product.setCompanyId(companyId);
			
			StatusMain statusMain=new StatusMain();
			statusMain.setStatusId(updatedStatus);
			product.setStatus(statusMain);
			productRepo.save(product);
			updateStatus=1;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return updateStatus;
	}

	@Override
	public Integer delete(Product product, String companyApiKey) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Integer companyId=null;
		Integer productId=null;
		String productName=null;
		Integer updateStatus=null;
		try {
			companyId=companyRepo.findByApiKey(companyApiKey);
			productName=product.getName();
			productId=productRepo.findByNameAndCompanyId(productName, companyId);
			Optional<Product> optionalProduct=  productRepo.findById(productId);
			Product productToDelete= optionalProduct.get();
			
			StatusMain statusMain=new StatusMain();
			statusMain.setStatusId(StatusMap.DELETE);
			productToDelete.setStatus(statusMain);
			
			productRepo.save(productToDelete);
			updateStatus=1;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		logger.info("End of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		return updateStatus;
	}

	@Override
	public Product saveOrUpdate(ProductCommand productCommand, String apiKey) {
		logger.info("Start of-"+this.getClass().getName()+" "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method");
		Integer companyId=null;
		Integer productIdToUpdate=null;
		String updateProductName=null;
		String updatedProductDescription=null;
		Integer updatedStatus=null;
		Product product=null;
		Product saveOrUpdatedProduct=null;
		try {
			companyId=companyRepo.findByApiKey(apiKey);
			productIdToUpdate=productRepo.findByNameAndCompanyId(productCommand.getOldProductName(), companyId);
			updateProductName=productCommand.getProductName();
			updatedProductDescription=productCommand.getDescription();
			updatedStatus=productCommand.getStatusId();

			if(productIdToUpdate==null) {
				//create new Product 
				product= commandToProduct.convert(productCommand);
				product.setCompanyId(companyId);
			}else {
				//update existing product
				Optional<Product> optionalProduct=productRepo.findById(productIdToUpdate);
				product=optionalProduct.get();
				product.setProductId(productIdToUpdate);
				product.setName(updateProductName);
				product.setDescription(updatedProductDescription);
				product.setUpdatedDate(new Date());
				product.setCompanyId(companyId);
				
				StatusMain statusMain=new StatusMain();
				statusMain.setStatusId(updatedStatus);
				product.setStatus(statusMain);
			}
			saveOrUpdatedProduct= productRepo.save(product);
			logger.info("##Saved Or Updated Product Is --->"+saveOrUpdatedProduct);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " --> "+ Thread.currentThread().getStackTrace()[1].getMethodName()
                    + " --> Error is : " + e.getMessage(),e); 
		}
		return saveOrUpdatedProduct;
	}

}
