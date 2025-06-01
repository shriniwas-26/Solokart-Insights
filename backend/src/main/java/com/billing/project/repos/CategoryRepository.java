package com.billing.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billing.project.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
