package com.revature.services;

import com.revature.DAOs.ItemDAO;
import com.revature.DAOs.UserDAO;
import com.revature.exceptions.InvalidAuthenticationException;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.models.Item;
import com.revature.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemDAO itemDAO;
    private UserDAO userDAO;

    public ItemService(ItemDAO itemDAO, UserDAO userDAO)
    {
        this.itemDAO = itemDAO;
        this.userDAO = userDAO;
    }

    /*
     * This function allows a user to retrieve all items from the database.
     * The function returns a list of items to be returned in the response body by the ItemController.
     */
    public List<Item> getAllItems(String token) throws InvalidAuthenticationException
    {
        List<Item> toRet = new ArrayList<Item>();
        User user = userDAO.findByToken(token).orElseThrow(() -> new InvalidAuthenticationException("Invalid token!"));
        if(user.getIsAdmin() == true)
        {
            toRet.addAll(itemDAO.findAll());
        }
        else{
            Optional<List<Item>> mightBeAList = itemDAO.findByUserId(user.getUserId());
            if(mightBeAList.isPresent())
            {
                toRet.addAll(mightBeAList.get());
            }
        } 
        return toRet;   
    }

    /*
     * This function allows a user to retrieve an item by passing the item's id in the request body.
     * The item must have an id that already exists in the database. An ItemNotFoundException will be thrown
     * if that id does not exist.
     * The function returns the item requested to be returned in the response body by the ItemController. 
     */
    public Item getItemById(int id, String token) throws ItemNotFoundException, InvalidAuthenticationException
    {
        User user = userDAO.findByToken(token).orElseThrow(() -> new InvalidAuthenticationException("Invalid token!"));
        itemDAO.findByUserId(user.getUserId()).orElseThrow(() -> new InvalidAuthenticationException("Not your item!"));
        return itemDAO.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found!"));
    }

    /*
     * This function allows a user to add a new item to the database.
     * The function returns the new saved item to be returned in the response body by the ItemController.
     */
    public Item addItem(Item item, String token) throws InvalidAuthenticationException
    {
        User user = userDAO.findByToken(token).orElseThrow(() -> new InvalidAuthenticationException("Invalid token!"));
        item.setUser(user);
        return itemDAO.save(item);
    }

    /*
     * This function allows a user to update an item by passing the item to be updated in the request body.
     * The item must have an id that already exists in the database. An ItemNotFoundException will be thrown
     * if that id does not exist.
     * The function returns the updated item to be returned in the response body by the ItemController.
     */
    public Item updateItemById(Item item, String token) throws ItemNotFoundException, InvalidAuthenticationException
    {
        User user = userDAO.findByToken(token).orElseThrow(() -> new InvalidAuthenticationException("Invalid token!"));
        itemDAO.findByUserId(user.getUserId()).orElseThrow(() -> new InvalidAuthenticationException("Not your item!"));
        Item toChange = itemDAO.findById(item.getItemId()).orElseThrow(() -> new ItemNotFoundException("Item not found!"));
        toChange.setName(item.getName());
        toChange.setBiggerThanBreadBox(item.isBiggerThanBreadBox());
        itemDAO.save(toChange);
        return toChange;
    }
    /*
     * This function allows a user to update an item's name by passing the item's id and the new name in the request body.
     * The item must have an id that already exists in the database. An ItemNotFoundException will be thrown
     * if that id does not exist.
     * The function returns the updated item to be returned in the response body by the ItemController.
     */
    public Item updateItemNameById(int id, String newName, String token) throws ItemNotFoundException, InvalidAuthenticationException
    {
        User user = userDAO.findByToken(token).orElseThrow(() -> new InvalidAuthenticationException("Invalid token!"));
        itemDAO.findByUserId(user.getUserId()).orElseThrow(() -> new InvalidAuthenticationException("Not your item!"));
        Item toRet = itemDAO.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found!"));
        toRet.setName(newName);
        itemDAO.save(toRet);
        return toRet;
    }
    /*
     * This function allows a user to update an item's size by passing the item's id and the new size in the request body.
     * The item must have an id that already exists in the database. An ItemNotFoundException will be thrown
     * if that id does not exist.
     * The function returns the updated item to be returned in the response body by the ItemController.
     */
    public Item updateItemSizeById(int id, boolean newSize, String token) throws ItemNotFoundException, InvalidAuthenticationException
    {
        User user = userDAO.findByToken(token).orElseThrow(() -> new InvalidAuthenticationException("Invalid token!"));
        itemDAO.findByUserId(user.getUserId()).orElseThrow(() -> new InvalidAuthenticationException("Not your item!"));
        Item toRet = itemDAO.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found!"));
        toRet.setBiggerThanBreadBox(newSize);
        itemDAO.save(toRet);
        return toRet;
    }
    /*
     * This function allows a user to delete an item by passing the item's id.
     * The item must have an id that already exists in the database. An ItemNotFoundException will be thrown
     * if that id does not exist.
     * The function returns the deleted item to be returned in the response body by the ItemController.
     */
    public Item deleteItemById(int id, String token) throws ItemNotFoundException, InvalidAuthenticationException
    {
        User user = userDAO.findByToken(token).orElseThrow(() -> new InvalidAuthenticationException("Invalid token!"));
        itemDAO.findByUserId(user.getUserId()).orElseThrow(() -> new InvalidAuthenticationException("Not your item!"));
        Item toDel = new Item();
        toDel = itemDAO.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found!"));
        itemDAO.deleteById(id);
        return toDel;
    }
}
