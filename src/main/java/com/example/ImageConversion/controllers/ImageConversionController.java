package com.example.ImageConversion.controllers;

import com.example.ImageConversion.dto.FileResponse;
import com.example.ImageConversion.services.ImageConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class ImageConversionController {
    private ImageConversionService imageConversion;

    public ImageConversionController (ImageConversionService imageConversion){
        this.imageConversion = imageConversion;
    }


    public ResponseEntity<?> convertImageToAscii(@RequestParam("file") MultipartFile file) throws Exception{
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Пустой файл");
        }
        var fileData = file.getBytes();
      var result = imageConversion.convertToAscii(fileData);
      var fileResponse = new FileResponse(result);
        return ResponseEntity.status(HttpStatus.OK).body(fileResponse);

    }
}
