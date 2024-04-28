package com.ziraitakip.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="title")
public class Title {
    static public String TABLE = "title";
    static public String ALIAS = "t";
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "title")
    private List<User> users;
    public Title() {
        super();
    }
}
