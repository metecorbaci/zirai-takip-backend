package com.ziraitakip.backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name="`user`")
public class User{
    static public String TABLE = "user";
    static public String ALIAS = "u";
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean is_active = false;
    @Column(nullable = true)
    private String activation_code;
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
    public User(String email,String first_name,String last_name,String password){
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getActivation_code() {
        return activation_code;
    }

    public void setActivation_code(String activation_code) {
        this.activation_code = activation_code;
    }

    public String getFullName(){
        return this.first_name + " " + this.last_name;
    }
}
