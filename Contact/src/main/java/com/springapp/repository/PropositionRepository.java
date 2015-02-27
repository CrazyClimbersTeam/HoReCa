package com.springapp.repository;

import com.springapp.model.Places;
import com.springapp.model.SpecialProposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropositionRepository extends JpaRepository<SpecialProposition, Long> {
    List<SpecialProposition> findAll();
}
