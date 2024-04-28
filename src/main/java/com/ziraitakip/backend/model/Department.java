package com.ziraitakip.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="department")
public class Department{
    static public String TABLE = "department";
    static public String ALIAS = "d";
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @OneToMany(mappedBy = "department")
    private List<User> users;
    public Department() {
        super();
    }
}
