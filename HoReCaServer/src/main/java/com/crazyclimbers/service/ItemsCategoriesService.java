package com.crazyclimbers.service;

import com.crazyclimbers.model.ItemsCategories;
import com.crazyclimbers.repository.ItemsCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
public class ItemsCategoriesService {

    @Autowired
    ItemsCategoriesRepository itemsCategoriesRepository;

    public List<ItemsCategories> findAll() {
        return itemsCategoriesRepository.findAll();
    }


}
