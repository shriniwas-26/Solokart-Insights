package com.billing.project.dto;

import com.billing.project.entities.Category;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {
	
    private String name;
    
    private Long categoryId;
    
    private Double price;
    
    private String description;
    
    private String imageUrl;
}
