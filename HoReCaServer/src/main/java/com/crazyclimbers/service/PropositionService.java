package com.crazyclimbers.service;

import com.crazyclimbers.model.Proposition;
import com.crazyclimbers.repository.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PropositionService {

    @Autowired
    PropositionRepository propositionRepository;


    public List<Proposition> findAll() {
        return propositionRepository.findAll();
    }


}
