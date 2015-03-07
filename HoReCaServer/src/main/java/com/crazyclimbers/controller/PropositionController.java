package com.crazyclimbers.controller;


import com.crazyclimbers.model.Proposition;
import com.crazyclimbers.service.PropositionService;
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
    public List<Proposition> readCategory() {
        return propositionService.findAll();
    }

}