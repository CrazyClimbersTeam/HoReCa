package com.springapp.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Transient
    List<Items>itemses;
}
