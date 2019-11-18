package com.sutisoft.contentmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sutisoft.contentmanagement.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("select productId from Product where name =:productName and companyId=:companyId and status.statusId=1")
	Integer findByNameAndCompanyId(@Param("productName") String productName,
			@Param("companyId") Integer companyId);
}
