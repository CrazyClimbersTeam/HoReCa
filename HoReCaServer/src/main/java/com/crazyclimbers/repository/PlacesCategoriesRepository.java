package com.crazyclimbers.repository;

import com.crazyclimbers.model.PlacesCategories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlacesCategoriesRepository extends JpaRepository<PlacesCategories, Long> {

    List<PlacesCategories> findAll();

}
