package com.fileservice.repository;


import com.fileservice.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    File save(File file);
    File findFileByUserEmailAndType_Code(String email, String code);
}


