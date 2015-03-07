package com.crazyclimbers.repository;

import com.crazyclimbers.model.ItemsCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemsCategoriesRepository extends JpaRepository<ItemsCategories, Long> {

    List<ItemsCategories> findAll();

}
