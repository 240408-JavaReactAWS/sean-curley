package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.InvalidAuthenticationException;
import com.revature.exceptions.InvalidRegistrationException;
import com.revature.exceptions.UserAlreadyExistsException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUserHandler(@RequestBody User user)
    {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginHandler(@RequestBody User user)
    {
        return ResponseEntity.ok(userService.login(user.getUsername(), user.getPassword()));
    }

    @PostMapping("/logout")
    public ResponseEntity<User> logoutHandler(@RequestParam String token)
    {
        return ResponseEntity.ok(userService.logout(token));
    }

    @ExceptionHandler(InvalidRegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleInvalidRegistration(InvalidRegistrationException e)
    {
        return e.getMessage();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody String handleUserAlreadyExists(UserAlreadyExistsException e)
    {
        return e.getMessage();
    }
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody String handleMessageNotFound(UserNotFoundException e)
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