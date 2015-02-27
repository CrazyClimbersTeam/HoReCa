package com.springapp.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity (name = "places")
public class Places{
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
