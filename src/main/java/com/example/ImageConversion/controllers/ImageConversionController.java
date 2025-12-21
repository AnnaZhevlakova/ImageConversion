package com.example.ImageConversion.controllers;

import com.example.ImageConversion.dto.FileResponse;
import com.example.ImageConversion.services.ImageConversionService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
//@Tag(name = "Image Conversion", description = "API для конвертации изображений в ASCII арт")
public class ImageConversionController {
    private ImageConversionService imageConversion;

    public ImageConversionController(ImageConversionService imageConversion) {
        this.imageConversion = imageConversion;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> convertImageToAscii(@RequestParam("file")
                                                 @Schema(type = "string", format = "binary")
                                                 MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("Пустой файл");
        }
        var fileData = file.getBytes();
        var result = imageConversion.convertToAscii(fileData);
        var fileResponse = new FileResponse(result);
        return ResponseEntity.status(HttpStatus.OK).body(fileResponse);

    }
}
