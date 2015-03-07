package com.crazyclimbers.service;

import com.crazyclimbers.model.MenuItem;
import com.crazyclimbers.model.Place;
import com.crazyclimbers.model.Proposition;
import com.crazyclimbers.repository.PlacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by damvol on 07.03.2015.
 */
@Service
@Transactional
public class PlacesService {

    @Autowired
    PlacesRepository placesRepository;


    public List<Place> findAll() {
        return placesRepository.findAll();
    }

    public List<Place> placeByCategoryId(Long categoryId) {
        return placesRepository.placeByCategoryId(categoryId);
    }

    public List<Place> placeByCategoryNameList(List<String> listCategoryName) {
        return placesRepository.placeByCategoryNameList(listCategoryName);
    }

    public List<Place> placeByCategoryDescription(String desc) {
        return placesRepository.placeByCategoryDescription(desc);
    }

    public List<MenuItem> menuByPlaceId(Long id) {
        return placesRepository.menuByPlaceId(id);
    }

    public List<Proposition> specialProposition() {
        return placesRepository.specialProposition();
    }


}
