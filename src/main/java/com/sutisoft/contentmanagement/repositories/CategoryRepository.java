package com.sutisoft.contentmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sutisoft.contentmanagement.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select categoryId from Category where name =:categoryName and companyId=:companyId and status.statusId=1 ")
	Integer findByNameAndCompanyId(@Param("categoryName") String categoryName,
			@Param("companyId") Integer companyId);

}
