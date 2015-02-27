package com.springapp.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "items")
public class Items{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Double price;
    String descr;

    @ManyToOne
    @JoinColumn
    Category category;

    @ManyToOne
    @JoinColumn
    Places places;

    String categoryMenu;
}
