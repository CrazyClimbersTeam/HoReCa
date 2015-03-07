package com.crazyclimbers.controller;


import com.crazyclimbers.model.MenuItem;
import com.crazyclimbers.service.MenuItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping(value = "menuitems/")
@RestController
public class MenuItemsController {

    @Autowired
    MenuItemsService itemsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<MenuItem> readPlaces() {
        return itemsService.findAll();
    }

}