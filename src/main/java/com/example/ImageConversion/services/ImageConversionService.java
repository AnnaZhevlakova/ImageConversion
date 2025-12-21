package com.example.ImageConversion.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("request")
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
