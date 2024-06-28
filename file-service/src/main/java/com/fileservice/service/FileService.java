package com.fileservice.service;

import com.common.constant.FileConstant;
import com.common.constant.ResponseMessages;
import com.common.util.ResponseBody;
import com.fileservice.dto.FileReqDto;
import com.fileservice.model.FileType;
import com.fileservice.repository.FileRepository;
import com.fileservice.repository.FileTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileTypeRepository fileTypeRepository;

    public ResponseEntity uploadFile(FileReqDto fileReqDto, MultipartFile file) {
        try {
            // Dizin yolunu dinamik olarak oluşturun
            if (file.isEmpty()) {
                return ResponseBody.send(ResponseMessages.FILE_EMPTY(), null, HttpStatus.NOT_FOUND);
            }
            FileType fileType = fileTypeRepository.findFileTypeByCode(fileReqDto.getTypeCode());
            if (fileType == null) {
                return ResponseBody.send(ResponseMessages.FILE_TYPE_NOT_FOUND(), null, HttpStatus.NOT_FOUND);
            }
            if (!FileConstant.ALLOWED_TYPES.containsKey(file.getContentType())) {
                return ResponseBody.send(ResponseMessages.FILE_TYPE_NOT_ALLOWED(), null, HttpStatus.BAD_REQUEST);
            }
            if (fileType.getCode().equals(FileConstant.PROFILE_PHOTO_TYPE)) {
                // Kullanıcı profil fotoğrafı yüklendiğinde eski profil fotoğrafını sil
                // Eski profil fotoğrafını silmek için kullanıcı bilgilerini alın
                // Eski profil fotoğrafının adını ve yolunu alın
                // Eski profil fotoğrafını silin
                com.fileservice.model.File oldPPFile = fileRepository.findFileByUserEmailAndType_Code(fileReqDto.getUserEmail(), FileConstant.PROFILE_PHOTO_TYPE);
                System.out.println("file ::" + oldPPFile.getUserEmail());
                if (oldPPFile != null) {
                    String basePath = Paths.get("").toAbsolutePath().normalize().toString();
                    String uploadPath = Paths.get(basePath, FileConstant.FILE_UPLOAD_DEST).normalize().toString();
                    String oldPPPath = uploadPath + "/" + oldPPFile.getPath();
                    File oldPP = new File(oldPPPath);
                    if (oldPP.exists()) {
                        // Eski profil fotoğrafı dosyalardan siliniyor
                        oldPP.delete();
                        // Eski profil fotoğrafı veritabanından siliniyor
                        fileRepository.delete(oldPPFile);
                    }
                }
            }
            var userMail = SecurityContextHolder.getContext().getAuthentication().getName();

            String basePath = Paths.get("").toAbsolutePath().normalize().toString();
            String uploadPath = Paths.get(basePath, FileConstant.FILE_UPLOAD_DEST).normalize().toString();
            // File Type kontrol ediliyor

            String fullPath = uploadPath + "/" + fileReqDto.getUserEmail();
            Files.createDirectories(Paths.get(fullPath));
            String uniqueFileName = new Date().getTime() + "_" + fileReqDto.getTypeCode() + "." + FileConstant.ALLOWED_TYPES.get(file.getContentType());
            File dest = new File(fullPath + "/" + uniqueFileName);
            file.transferTo(dest);

            com.fileservice.model.File fileEntity = new com.fileservice.model.File();
            fileEntity.setName(uniqueFileName);
            fileEntity.setPath(fileReqDto.getUserEmail() + "/" + uniqueFileName);
            fileEntity.setType(fileType);
            fileEntity.setUserEmail(fileReqDto.getUserEmail());

            Object savedFile = fileRepository.save(fileEntity);
            if (savedFile == null) {
                return ResponseBody.send(ResponseMessages.FILE_UPLOAD_FAILED(), null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ResponseBody.send(ResponseMessages.FILE_UPLOAD_SUCCESS(), null, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseBody.send(ResponseMessages.FILE_UPLOAD_FAILED(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getFileTypes() {
        try {
            List<FileType> fileTypes = fileTypeRepository.findAll();
            if (fileTypes.isEmpty()) {
                return ResponseBody.send(ResponseMessages.FILE_TYPE_NOT_FOUND(), null, HttpStatus.NOT_FOUND);
            }
            return ResponseBody.send(null, fileTypes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBody.send(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void downloadFile() {
        // download file
    }

}
