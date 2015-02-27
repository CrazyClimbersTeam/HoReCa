package com.springapp.repository;

import com.springapp.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemsRepository extends JpaRepository<Items, Long> {
    List<Items> findAll();

    List<Items> findByCategoryId(Long id);
}
