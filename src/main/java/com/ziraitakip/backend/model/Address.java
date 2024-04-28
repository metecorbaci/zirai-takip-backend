package com.ziraitakip.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="address")
public class Address{
    static String TABLE = "address";
    static String ALIAS = "ad";
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column()
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="district_id" , referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_ad_district_id"))
    private District district;
    @OneToMany(mappedBy = "address")
    private List<Company> companies;
    @OneToMany(mappedBy = "address")
    private List<Branch> branches;
    public Address() {
        super();
    }

}
