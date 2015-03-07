package com.crazyclimbers.model;


import lombok.Data;
import javax.persistence.*;



@Data
@Entity(name = "itemscategories")
public class ItemsCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

}
