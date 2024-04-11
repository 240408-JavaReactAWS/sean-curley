

package com.revature.controllers;

import com.revature.exceptions.ItemNotFoundException;
import com.revature.services.ItemService;
import com.revature.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController //Bean to convert HTTP requests
@RequestMapping("/items") //Any HTTP request ending in /items will go to this app
@ResponseBody //Turns response data into JSON automatically
public class ItemController {

    @Autowired
    private ItemService itemService;

    public ItemController(ItemService itemService)
    {
        this.itemService = itemService;
    }

    @PostMapping()
    public ResponseEntity<Item> addItemHandler(@RequestBody Item item)
    {
        return ResponseEntity.ok(itemService.addItem(item));
    }

    @GetMapping()
    public ResponseEntity<List<Item>> getAllItemsHandler()
    {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemByIdHandler(@PathVariable int id)
    {
        try{
            Item toRet = itemService.getItemById(id);
            return ResponseEntity.ok(toRet);
        }
        catch(ItemNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping()
    public ResponseEntity<Item> updateItemByIdHandler(@RequestBody Item item)
    {
        try{
            Item toUpdate = itemService.updateItemById(item);
            return ResponseEntity.ok(toUpdate);
        }
        catch(ItemNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> deleteItemByIdHandler(@PathVariable int id)
    {
        try{
            Item toRet = itemService.deleteItemById(id);
            return ResponseEntity.ok(toRet);
        }
        catch(ItemNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}