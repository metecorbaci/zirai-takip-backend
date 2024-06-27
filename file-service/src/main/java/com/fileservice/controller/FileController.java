package com.fileservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public void uploadFile() {
        // upload file
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
