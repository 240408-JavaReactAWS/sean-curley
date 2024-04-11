package com.revature.services;

import com.revature.DAOs.ItemDAO;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemDAO itemDAO;

    public ItemService(ItemDAO itemDAO)
    {
        this.itemDAO = itemDAO;
    }

    public List<Item> getAllItems()
    {
        return itemDAO.findAll();
    }

    public Item getItemById(int id) throws ItemNotFoundException
    {
        return itemDAO.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found!"));
    }

    public Item addItem(Item item)
    {
        return itemDAO.save(item);
    }

    public Item updateItemById(Item item) throws ItemNotFoundException
    {
        Item toChange = itemDAO.findById(item.getItemId()).orElseThrow(() -> new ItemNotFoundException("Item not found!"));
        itemDAO.save(item);
        return item;
    }

    public Item updateItemNameById(int id, String newName) throws ItemNotFoundException
    {
        Item toRet = itemDAO.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found!"));
        toRet.setName(newName);
        itemDAO.save(toRet);
        return toRet;
    }

    public Item updateItemSizeById(int id, boolean newSize) throws ItemNotFoundException
    {
        Item toRet = itemDAO.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found!"));
        toRet.setBiggerThanBreadBox(newSize);
        itemDAO.save(toRet);
        return toRet;
    }

    public Item deleteItemById(int id) throws ItemNotFoundException
    {
        Item toDel = new Item();
        toDel = itemDAO.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found!"));
        itemDAO.deleteById(id);
        return toDel;
    }
}
