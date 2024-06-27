package com.fileservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "`file`")
public class File {
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String path;
    @Column(nullable = false)
    private String user_email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="type_id" , referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_f_type_id"))
    private FileType type;
}
