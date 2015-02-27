package com.springapp.repository;

import com.springapp.model.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAll();

    Category findCategoryById(Long id);

    List<Category> menuByCategoryOfPlace(Long id);

    @Query(value = "SELECT DISTINCT categories.* FROM items INNER JOIN items ON places.id = items.places_id INNER JOIN categories ON items.category_id = categories.id WHERE categories.name IN (?1)\", nativeQuery = true)\n", nativeQuery = true)
    List<Category> getMenu(Long id);
}
