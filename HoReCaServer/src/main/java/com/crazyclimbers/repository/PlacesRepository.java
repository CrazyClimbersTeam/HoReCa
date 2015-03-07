package com.crazyclimbers.repository;

/**
 * Created by damvol on 07.03.2015.
 */

import com.crazyclimbers.model.MenuItem;
import com.crazyclimbers.model.Place;
import com.crazyclimbers.model.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlacesRepository extends JpaRepository<Place, Long> {
    List<Place> findAll();

    @Query(value = "SELECT DISTINCT places.* FROM places INNER JOIN menuitems ON places.id = menuitems.places_id WHERE menuitems.itemsCategories_id = ?1", nativeQuery = true)
    List<Place> placeByCategoryId(Long id);

    @Query(value = "SELECT DISTINCT places.* FROM places INNER JOIN menuitems ON places.id = menuitems.places_id INNER JOIN itemscategories ON menuitems.itemsCategories_id = itemscategories.id WHERE itemscategories.name IN (?1)", nativeQuery = true)
    List<Place> placeByCategoryNameList(List<String> listname);

    @Query(value = "SELECT DISTINCT places.* FROM places WHERE places.short_descr like ?1", nativeQuery = true)
    List<Place> placeByCategoryDescription(String desc);

    @Query(value = "SELECT menuitems.* FROM menuitems WHERE menuitems.places_id=?1", nativeQuery = true)
    List<MenuItem> menuByPlaceId (Long id);

    @Query(value = "SELECT propositions.* FROM propositions", nativeQuery = true)
    List<Proposition> specialProposition();


}
