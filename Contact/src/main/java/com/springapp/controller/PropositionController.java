package com.springapp.controller;


import com.springapp.model.Category;
import com.springapp.model.SpecialProposition;
import com.springapp.service.CategoryService;
import com.springapp.service.PropositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping(value = "proposition/")
@RestController
public class PropositionController {

    @Autowired
    PropositionService propositionService;

    @RequestMapping(method = RequestMethod.GET)
    public List<SpecialProposition> readCategory() {
        return propositionService.findAll();
    }

}