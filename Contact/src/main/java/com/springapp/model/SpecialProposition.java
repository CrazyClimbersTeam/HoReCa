package com.springapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "specialProposition")
public class SpecialProposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String proposition;

}
