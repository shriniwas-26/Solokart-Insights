package com.billing.project.services;

import java.util.List;

import com.billing.project.dto.ApiResponse;
import com.billing.project.dto.ItemRequest;
import com.billing.project.entities.Item;

public interface ItemService {
	
    ApiResponse deleteItem(Long id);
    List<Item> getAllItems();
    Item getItemById(Long id);
    List<Item> getItemsByCategory(Long categoryId);
    List<Item> searchItems(String query);
	Item createItem(ItemRequest item);
	Item updateItem(Long id, ItemRequest itemDetails);
}
