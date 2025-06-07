package com.billing.project.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.billing.project.custom_exception.ResourceNotFoundException;
import com.billing.project.dto.ApiResponse;
import com.billing.project.dto.ItemRequest;
import com.billing.project.entities.Category;
import com.billing.project.entities.Item;
import com.billing.project.repos.CategoryRepository;
import com.billing.project.repos.ItemRepository;
import com.billing.project.services.ItemService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Item createItem(ItemRequest itemDto) {
    	Item item = modelMapper.map(itemDto, Item.class);
        Category category = categoryRepository.findById(itemDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + itemDto.getCategoryId()));
        
        
        item.setCategory(category);
        Item savedItem = itemRepository.save(item);
        return savedItem; 
    }

    @Override
    public Item updateItem(Long id, ItemRequest itemDetails) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
        
        Category category = categoryRepository.findById(itemDetails.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + itemDetails.getCategoryId()));
        
        item.setName(itemDetails.getName());
        item.setCategory(category);
        item.setPrice(itemDetails.getPrice());
        item.setDescription(itemDetails.getDescription());
        item.setImageUrl(itemDetails.getImageUrl());
        
        Item savedItem = itemRepository.save(item);
        return savedItem;
    }

    @Override
    public ApiResponse deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
        itemRepository.delete(item);
        return new ApiResponse("Item deleted successfully...");
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    @Override
    public List<Item> getItemsByCategory(Long categoryId) {
        return itemRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Item> searchItems(String query) {
        return itemRepository.searchItems(query);
    }
}