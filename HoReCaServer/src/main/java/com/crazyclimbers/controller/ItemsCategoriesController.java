package com.crazyclimbers.controller;


import com.crazyclimbers.model.ItemsCategories;
import com.crazyclimbers.service.ItemsCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping(value = "itemscategories/")
@RestController
public class ItemsCategoriesController {

    @Autowired
    ItemsCategoriesService itemsCategoriesService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ItemsCategories> readItemsCategories() {
        return itemsCategoriesService.findAll();
    }

}