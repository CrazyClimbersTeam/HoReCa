package com.crazyclimbers.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "menuitems")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Double price;
    String descr;

    @ManyToOne
    @JoinColumn
    ItemsCategories category;

    @ManyToOne
    @JoinColumn
    Place places;

    String categoryMenu;
}
