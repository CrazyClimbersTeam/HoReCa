package com.crazyclimbers.service;

import com.crazyclimbers.model.MenuItem;
import com.crazyclimbers.repository.MenuItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class MenuItemsService {

    @Autowired
    MenuItemsRepository itemsRepository;

    public List<MenuItem> findAll() {
        return itemsRepository.findAll();
    }

    public List<MenuItem> findCategoryId(Long id){
        return itemsRepository.findByCategoryId(id);
    }


}
