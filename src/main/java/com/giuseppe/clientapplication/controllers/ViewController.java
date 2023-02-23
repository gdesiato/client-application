package com.giuseppe.clientapplication.controllers;

import com.giuseppe.clientapplication.models.User;
import com.giuseppe.clientapplication.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class ViewController {

    @Autowired
    ItemService itemService;

    @Autowired
    // the RestTemplate allows me to make a call across microservices
    private RestTemplate restTemplate;

    @GetMapping("/items")
    public String displayItems(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "item-list";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registration-form";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute("user") User user) {
        // Process the registration form submission, save the user to the database, etc.
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        // Add model attributes for the login form
        return "login-form";
    }

    @PostMapping("/login")
    public String processLoginForm(@RequestParam("username") String username, @RequestParam("password") String password) {
        // Process the login form submission, authenticate the user, etc.
        return "redirect:/items";
    }

    // we use RestTemplate to connect the UserMicroservice
    @GetMapping("/users")
    public List<Object> getUser() {
        ResponseEntity<Object[]> response = restTemplate.getForEntity("http://USER-MICROSERVICE/user", Object[].class);
        System.out.println(response);

        Object[] users = restTemplate.getForObject("http://USER-MICROSERVICE/user", Object[].class);
        return Arrays.asList(users);
    }


}
