package com.crazyclimbers.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by damvol on 07.03.2015.
 */
@Data
@Entity(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String short_descr;
    String info;
    String address;
    String telephone;
    Integer rating;
    String logo;
    String photo;
    String lat;
    String lng;
    Long distance;
}
