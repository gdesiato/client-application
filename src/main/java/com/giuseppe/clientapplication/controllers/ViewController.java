package com.giuseppe.clientapplication.controllers;

import com.giuseppe.clientapplication.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @Autowired
    ItemService itemService;

    @GetMapping("/items")
    public String displayItems(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "item-list";
    }
}
