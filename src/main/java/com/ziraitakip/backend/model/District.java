package com.ziraitakip.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="district")
public class District {
    static public String TABLE = "district";
    static public String ALIAS = "di";
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="city_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_di_city_id"))
    private City city;
    @OneToMany(mappedBy = "district")
    private List<Address> addresses;
    public District() {
        super();
    }
}
