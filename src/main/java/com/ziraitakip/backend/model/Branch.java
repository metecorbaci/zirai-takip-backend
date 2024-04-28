package com.ziraitakip.backend.model;

import jakarta.persistence.*;

import java.util.List;
@Entity(name="branch")
public class Branch{
    static public String TABLE = "branch";
    static public String ALIAS = "b";
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id" , referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_b_company_id"))
    private Company company;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address_id" , referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_b_address_id"))
    private Address address;
    @OneToMany(mappedBy = "branch")
    private List<User> users;

    public Branch() {
        super();
    }
}
