package com.fileservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "`file`")
@Table(
        uniqueConstraints =
                {@UniqueConstraint(name = "ui_name", columnNames = {"name"}),
                        @UniqueConstraint(name = "ui_code", columnNames = {"code"})}
)
public class File {
    @Id()
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String path;
    @Column(nullable = false)
    private String userEmail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="type_id" , referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_f_type_id"))
    private FileType type;
}
