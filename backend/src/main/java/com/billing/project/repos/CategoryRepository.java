package com.billing.project.repos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billing.project.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	boolean existsByName(String name);
}
