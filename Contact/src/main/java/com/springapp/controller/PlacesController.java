package com.springapp.controller;


import com.springapp.model.Category;
import com.springapp.model.Items;
import com.springapp.model.Places;
import com.springapp.model.SpecialProposition;
import com.springapp.service.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RequestMapping(value = "places/")
@RestController
public class PlacesController {

    @Autowired
    PlacesService placesService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Places> readPlaces() {
        return placesService.findAll();
    }

    @RequestMapping(value = "placebycategoryid/{id}", method = RequestMethod.GET)
    public List<Places> placeByCategoryId(@PathVariable Long id) {
        return placesService.placeByCategoryId(id);
    }


    @RequestMapping(value = "placebycategorynames/{array}", method = RequestMethod.GET)
    public List<Places> placeByCategoryNames(@PathVariable String array) {
        List<String> arrayName= Arrays.asList(array.split(" "));
        return placesService.placeByCategoryNameList(arrayName);
    }

    @RequestMapping(value = "placeByCategoryDescription/{desc}", method = RequestMethod.GET)
    public List<Places> placeByCategoryDescription(@PathVariable String desc) {
        return placesService.placeByCategoryDescription("%"+desc+"%");
    }

    @RequestMapping(value = "menuByPlaceId/{id}", method = RequestMethod.GET)
    public List<Items> menuByPlaceId(@PathVariable Long id) {
        return placesService.menuByPlaceId(id);
    }

    @RequestMapping(value = "specialProposition/", method = RequestMethod.GET)
    public List<SpecialProposition> specialProposition() {
        return placesService.specialProposition();
    }


}