package com.fileservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "file_type")
@Table(
        uniqueConstraints=
        @UniqueConstraint(name ="ui_name" ,columnNames={"name"})
)
public class FileType {
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "type")
    private List<File> files;
}
