package com.crazyclimbers.service;

import com.crazyclimbers.model.PlacesCategories;
import com.crazyclimbers.repository.PlacesCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PlacesCategoriesService {

    @Autowired
    PlacesCategoriesRepository placesCategoriesRepository;

    public List<PlacesCategories> findAll() {
        return placesCategoriesRepository.findAll();
    }


}
