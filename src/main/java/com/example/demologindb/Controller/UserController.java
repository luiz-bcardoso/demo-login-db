package com.example.demologindb.Controller;

import com.example.demologindb.Model.User;

import com.example.demologindb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/auth")
public class UserController {

    @Autowired
    private UserRepository repoUser;

    @GetMapping("/welcome")
    public String greeting() {
        return "welcome";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String registerUser(Model model){

        //User user = new User();
        model.addAttribute("usr", new User());

        // Send to the user's register form.
        return "registerUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user, Model model){

        //TODO: Make a condition to check if user by username/login is already registered on database.

        // Saves a new user on it's repository
        System.out.println(user.toString());

        // Encrypts password with BCrypto and saves it onto the objcet
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));


        repoUser.save(user);

        // Gets all users form repository on a list
        List<User> userList = (List<User>) repoUser.findAll();
        model.addAttribute("users", userList);

        // Return to the login form.
        return "login";
    }

}
