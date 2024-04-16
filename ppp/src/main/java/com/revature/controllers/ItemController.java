

package com.revature.controllers;

import com.revature.exceptions.InvalidAuthenticationException;
import com.revature.exceptions.InvalidRegistrationException;
import com.revature.exceptions.ItemNotFoundException;
import com.revature.services.ItemService;
import com.revature.services.UserService;
import com.revature.models.Item;
import com.revature.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController //Bean to convert HTTP requests
@RequestMapping("/items") //Any HTTP request ending in /items will go to this app
@ResponseBody //Turns response data into JSON automatically
public class ItemController {

    @Autowired
    private ItemService itemService;

    public ItemController(ItemService itemService, UserService userService)
    {
        this.itemService = itemService;
    }

    @PostMapping()
    public ResponseEntity<Item> addItemHandler(@RequestBody Item item, @RequestParam String token)
    {
        return ResponseEntity.ok(itemService.addItem(item, token));
    }

    @GetMapping()
    public ResponseEntity<List<Item>> getAllItemsHandler(@RequestParam String token)
    {
        return ResponseEntity.ok(itemService.getAllItems(token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemByIdHandler(@PathVariable int id, @RequestParam String token)
    {
        Item toRet = itemService.getItemById(id, token);
        return ResponseEntity.ok(toRet);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Item> updateItemByIdHandler(@PathVariable int id, @RequestBody Item item, @RequestParam String token)
    {
        Item toUpdate = itemService.updateItemById(id, item, token);
        return ResponseEntity.ok(toUpdate);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> deleteItemByIdHandler(@PathVariable int id, @RequestParam String token)
    {
        Item toRet = itemService.deleteItemById(id, token);
        return ResponseEntity.ok(toRet);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody String handleMessageNotFound(ItemNotFoundException e)
    {
        return e.getMessage();
    }
    @ExceptionHandler(InvalidAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody String handleInvalidUser(InvalidAuthenticationException e)
    {
        return e.getMessage();
    }
}