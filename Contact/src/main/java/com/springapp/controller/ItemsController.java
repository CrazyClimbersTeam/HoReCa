package com.springapp.controller;


import com.springapp.model.Items;
import com.springapp.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping(value = "items/")
@RestController
public class ItemsController {

    @Autowired
    ItemsService itemsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Items> readPlaces() {
        return itemsService.findAll();
    }

}