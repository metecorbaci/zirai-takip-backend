package com.fileservice.controller;

import com.common.constant.ResponseMessages;
import com.common.util.ResponseBody;
import com.fileservice.dto.FileReqDto;
import com.fileservice.service.FileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;
    @PostMapping("/upload")
    public ResponseEntity uploadFile(@Valid @RequestPart("metadata") FileReqDto fileReqDto, @RequestPart("file") MultipartFile file) {
        return fileService.uploadFile(fileReqDto, file);
    }

    @GetMapping("/types")
    public void getFileTypes() {
        // get file types
    }

    @GetMapping("/download")
    public void downloadFile() {
        // download file
    }

}
