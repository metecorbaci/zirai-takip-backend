package com.fileservice.repository;

import com.fileservice.model.FileType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileTypeRepository extends JpaRepository<FileType, Long> {
    FileType findFileTypeByCode(String code);
    FileType save(FileType fileType);
}
