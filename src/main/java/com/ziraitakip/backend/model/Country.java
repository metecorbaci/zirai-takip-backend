package com.ziraitakip.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="country")
public class Country{
    static public String TABLE = "country";
    static public String ALIAS = "ct";
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "country")
    private List<City> cities;
    public Country() {
        super();
    }
}
