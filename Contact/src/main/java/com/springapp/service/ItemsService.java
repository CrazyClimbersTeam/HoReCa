package com.springapp.service;

import com.springapp.model.Items;
import com.springapp.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ItemsService {

    @Autowired
    ItemsRepository itemsRepository;

    public List<Items> findAll() {
        return itemsRepository.findAll();
    }

    public List<Items> findCategoryId(Long id){
        return itemsRepository.findByCategoryId(id);
    }


}
