package com.fileservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "file_type")
@Table(
        uniqueConstraints =
                {@UniqueConstraint(name = "ui_name", columnNames = {"name"}),
                        @UniqueConstraint(name = "ui_code", columnNames = {"code"})}
)
public class FileType {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String code;

    @OneToMany(mappedBy = "type")
    private List<File> files;
}
