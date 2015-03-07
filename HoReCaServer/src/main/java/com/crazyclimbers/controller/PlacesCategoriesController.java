package com.crazyclimbers.controller;


import com.crazyclimbers.model.PlacesCategories;
import com.crazyclimbers.service.PlacesCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping(value = "placescategories/")
@RestController
public class PlacesCategoriesController {

    @Autowired
    PlacesCategoriesService placesCategoriesService;

    @RequestMapping(method = RequestMethod.GET)
    public List<PlacesCategories> readPlacesCategories() {
        return placesCategoriesService.findAll();
    }

}