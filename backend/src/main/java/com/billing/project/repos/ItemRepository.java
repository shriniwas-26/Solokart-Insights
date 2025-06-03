package com.billing.project.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.billing.project.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findByCategoryId(Long categoryId);
    
    @Query("SELECT i FROM Item i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Item> searchItems(@Param("query") String query);
}
