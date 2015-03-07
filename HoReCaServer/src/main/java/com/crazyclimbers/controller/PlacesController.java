package com.crazyclimbers.controller;

import com.crazyclimbers.model.MenuItem;
import com.crazyclimbers.model.Place;
import com.crazyclimbers.model.Proposition;
import com.crazyclimbers.service.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by damvol on 07.03.2015.
 */
@RequestMapping(value = "places/")
@RestController
public class PlacesController {

    @Autowired
    PlacesService placesService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Place> readPlaces() {
        return placesService.findAll();
    }

    @RequestMapping(value = "placebycategoryid/{id}", method = RequestMethod.GET)
    public List<Place> placeByCategoryId(@PathVariable Long id) {
        return placesService.placeByCategoryId(id);
    }


    @RequestMapping(value = "placebycategorynames/{array}", method = RequestMethod.GET)
    public List<Place> placeByCategoryNames(@PathVariable String array) {
        List<String> arrayName = Arrays.asList(array.split(" "));
        return placesService.placeByCategoryNameList(arrayName);
    }

    @RequestMapping(value = "placeByCategoryDescription/{desc}", method = RequestMethod.GET)
    public List<Place> placeByCategoryDescription(@PathVariable String desc) {
        return placesService.placeByCategoryDescription("%" + desc + "%");
    }

    @RequestMapping(value = "menuByPlaceId/{id}", method = RequestMethod.GET)
    public List<MenuItem> menuByPlaceId(@PathVariable Long id) {
        return placesService.menuByPlaceId(id);
    }

    @RequestMapping(value = "specialProposition/", method = RequestMethod.GET)
    public List<Proposition> specialProposition() {
        return placesService.specialProposition();
    }
}