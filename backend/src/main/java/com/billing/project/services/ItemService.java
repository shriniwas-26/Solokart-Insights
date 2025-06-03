package com.billing.project.services;

import java.util.List;

import com.billing.project.entities.Item;

public interface ItemService {
	Item createItem(Item item);
    Item updateItem(Long id, Item item);
    void deleteItem(Long id);
    List<Item> getAllItems();
    Item getItemById(Long id);
    List<Item> getItemsByCategory(Long categoryId);
    List<Item> searchItems(String query);
}
