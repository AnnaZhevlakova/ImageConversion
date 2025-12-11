package com.example.ImageConversion.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageConversionService {
    private TextColorSchemaService colorSchema;

    public ImageConversionService(TextColorSchemaService colorSchema){
        this.colorSchema = colorSchema;
    }

    public String convertToAscii(byte[] file){
        return null;
    }
}
