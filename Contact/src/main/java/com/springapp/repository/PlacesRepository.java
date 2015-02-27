package com.springapp.repository;

import com.springapp.model.Items;
import com.springapp.model.Places;
import com.springapp.model.SpecialProposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlacesRepository extends JpaRepository<Places, Long> {
    List<Places> findAll();

    @Query(value = "SELECT DISTINCT places.* FROM places INNER JOIN items ON places.id = items.places_id WHERE items.category_id = ?1", nativeQuery = true)
    List<Places> placeByCategoryId(Long id);

    @Query(value = "SELECT DISTINCT places.* FROM places INNER JOIN items ON places.id = items.places_id INNER JOIN categories ON items.category_id = categories.id WHERE categories.name IN (?1)", nativeQuery = true)
    List<Places> placeByCategoryNameList(List<String> listname);

    @Query(value = "SELECT DISTINCT places.* FROM places WHERE places.short_descr like ?1", nativeQuery = true)
    List<Places> placeByCategoryDescription(String desc);

    @Query(value = "SELECT items.* FROM items WHERE items.places_id=?1", nativeQuery = true)
    List<Items> menuByPlaceId (Long id);

    @Query(value = "SELECT specialProposition.* FROM specialProposition", nativeQuery = true)
    List<SpecialProposition> specialProposition();

    @Query(value = "SELECT items.* FROM items WHERE items.places_id=?1", nativeQuery = true)
    List<Items> menuByPlaceIdCategory (Long id);

}
