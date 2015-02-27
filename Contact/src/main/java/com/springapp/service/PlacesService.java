package com.springapp.service;

import com.springapp.model.Items;
import com.springapp.model.Places;
import com.springapp.model.SpecialProposition;
import com.springapp.repository.ItemsRepository;
import com.springapp.repository.PlacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PlacesService {

    @Autowired
    PlacesRepository placesRepository;
    @Autowired
    ItemsRepository itemsRepository;

    public List<Places> findAll() {
        return placesRepository.findAll();
    }

    public List<Places> placeByCategoryId(Long categoryId) {
        return placesRepository.placeByCategoryId(categoryId);
    }

    public List<Places> placeByCategoryNameList(List<String> listCategoryName) {
        return placesRepository.placeByCategoryNameList(listCategoryName);
    }

    public List<Places> placeByCategoryDescription(String desc) {
        return placesRepository.placeByCategoryDescription(desc);
    }



    public List<Items> menuByPlaceId(Long id) {
        return placesRepository.menuByPlaceId(id);
    }

    public List<SpecialProposition> specialProposition() {
        return placesRepository.specialProposition();
    }


}
