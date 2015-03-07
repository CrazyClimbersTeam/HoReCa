package com.crazyclimbers.repository;

import com.crazyclimbers.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemsRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findAll();

    List<MenuItem> findByCategoryId(Long id);
}
