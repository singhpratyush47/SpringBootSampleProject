package com.sutisoft.contentmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sutisoft.contentmanagement.domain.ContentManagement;
@Repository
public interface ContentManagementRepository extends JpaRepository<ContentManagement, Integer>{

	@Query("select contentManagementId from ContentManagement where product.name=:productName "
			+ "and category.name=:categoryName and companyId=:companyId and contentId=:contentId "
			+ "and status.statusId=:status")
	Integer findContentByProductAndCategory(@Param("productName") String productName,
			@Param("categoryName") String categoryName,@Param("companyId") Integer companyId,
			@Param("contentId") String contentId,@Param("status") Integer status);
	
	@Query("select c from ContentManagement c where c.product.name=:productName and c.companyId=:companyId "
			+ "and status.statusId=:status")
	List<ContentManagement> findByProductAndCompanyId(@Param("productName") String productName,
			@Param("companyId") Integer companyId,@Param("status") Integer status);
	
	@Query("select c from ContentManagement c where c.product.name=:productName "
			+ "and c.contentId=:contentId and c.companyId=:companyId and status.statusId=:status")
	List<ContentManagement> findByProductAndContentIdAndCompanyId(@Param("productName") String productName,
			@Param("contentId") String contentId,@Param("companyId") Integer companyId,@Param("status") Integer status);
}
