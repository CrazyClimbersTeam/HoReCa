package com.springapp.controller;


import com.springapp.model.Category;
import com.springapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping(value = "categories/")
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> readCategory() {
        return categoryService.findAll();
    }

    @RequestMapping(value = "getMenu", method = RequestMethod.GET)
    public List<Category> getMenu(@PathVariable Long id){
        return categoryService.getMenu(id);
    }
}