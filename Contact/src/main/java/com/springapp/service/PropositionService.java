package com.springapp.service;

import com.springapp.model.Category;
import com.springapp.model.SpecialProposition;
import com.springapp.repository.CategoryRepository;
import com.springapp.repository.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PropositionService {

    @Autowired
    PropositionRepository propositionRepository;


    public List<SpecialProposition> findAll() {
        return propositionRepository.findAll();
    }


}
