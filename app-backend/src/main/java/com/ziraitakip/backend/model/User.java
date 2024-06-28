package com.ziraitakip.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name="`user`")
@Data
@Table(
        uniqueConstraints=
        @UniqueConstraint(name ="ui_email" ,columnNames={"email"})
)
public class User{
    static public String TABLE = "user";
    static public String ALIAS = "u";
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean isActive = false;
    @Column(nullable = true)
    private String activationCode;
    @CreationTimestamp()
    private Date created_at;
    @UpdateTimestamp()
    private Date updated_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="branch_id" , referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_u_branch_id"))
    private Branch branch;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="title_id" , referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_u_title_id"))
    private Title title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id" , referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_u_role_id"))
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id" , referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_u_department_id"))
    private Department department;

    public User() {
        super();
    }
    public User(String email, String firstName, String lastName, String password){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

}
