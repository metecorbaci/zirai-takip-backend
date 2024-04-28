package com.ziraitakip.backend.model;

import jakarta.persistence.*;

@Entity(name="company")
public class Company{
    static public String TABLE = "company";
    static public String ALIAS = "co";
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phone_number1;
    @Column()
    private String phone_number2;
    @Column(nullable = false)
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_co_address_id"))
    private Address address;

    public Company() {
        super();
    }
}
