package com.revature.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.DAOs.UserDAO;
import com.revature.exceptions.InvalidAuthenticationException;
import com.revature.exceptions.InvalidRegistrationException;
import com.revature.exceptions.UserAlreadyExistsException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    private final String tokenChars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    public User getUserById(int id) throws UserNotFoundException
    {
        return userDAO.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }
    public User getUserByUserName(String username) throws UserNotFoundException
    {
        return userDAO.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }
    public User registerUser(User user) throws InvalidRegistrationException, UserAlreadyExistsException
    {
        String username = user.getUsername();
        String password = user.getPassword();
        if(username == null || password == null)
        {
            System.err.println("Something bad happened in the registration service.");
            throw new InvalidRegistrationException("Unable to register new user:" + 
                username + ". Username and password must be at least four characters.");
        }
        if(username.length() < 4 || password.length() < 4)
        {
            throw new InvalidRegistrationException("Unable to register new user:" + 
                username + ". Username and password must be at least four characters.");
        }
        else if(userDAO.findByUsername(username).isPresent())
        {
            throw new UserAlreadyExistsException("User " + username + " already exists!");
        }
        else
        {
            return userDAO.save(user);
        }
    }

    public User login(String username, String password) throws InvalidAuthenticationException
    {
        User toRet = userDAO.findByUsernameAndPassword(username, password).orElseThrow(() -> new InvalidAuthenticationException(
            "That username/password combination is not present in the database."));
        toRet.setToken(generateToken());
        userDAO.save(toRet);
        return toRet;
    }

    public User logout(String token) throws InvalidAuthenticationException
    {
        User toRet = userDAO.findByToken(token).orElseThrow(()-> new InvalidAuthenticationException("Could not find user for corresponding token."));
        toRet.setToken(null);
        userDAO.save(toRet);
        return toRet;
    }

    public User getUserByToken(String token) throws InvalidAuthenticationException
    {
        return userDAO.findByToken(token).orElseThrow(() -> new InvalidAuthenticationException("Could not find user for corresponding token."));
    }

    public boolean checkToken(String token)
    {
        return userDAO.findByToken(token).isPresent();
    }

    public String generateToken()
    {
        /*String toRet = "";
        Random random = new Random();
        for(int i = 0; i < 10; i++)
        {
            toRet += tokenChars.charAt(random.nextInt(tokenChars.length()));
        }
        */
        String toRet = "thisisatoken";
        return toRet;
    }
}