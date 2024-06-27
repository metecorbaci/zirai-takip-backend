package com.ziraitakip.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="city")
public class City{
    static public String TABLE = "city";
    static public String ALIAS = "c";
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="country_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_c_country_id"))
    private Country country;
    @OneToMany(mappedBy = "city")
    private List<District> districts;
    public City() {
        super();
    }
}
