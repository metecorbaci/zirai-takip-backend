package com.ziraitakip.base.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

//Her Entity sınıfı için ortak alanlar burada tanımlanır.
@MappedSuperclass()
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity implements Serializable {
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @CreationTimestamp()
    private Date created_at;
    @UpdateTimestamp()
    private Date updated_at;
    public BaseEntity() {
        super();
    }

    public Integer getId() {
        return id;
    }
    public Date getCreated_at() {
        return created_at;
    }
    public Date getUpdated_at() {
        return updated_at;
    }
}
