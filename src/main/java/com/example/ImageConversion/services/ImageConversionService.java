package com.example.ImageConversion.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("request")
@Service
public class ImageConversionService {
    private TextColorSchemaService colorSchema;
    private TextGraphicsConverterService converterService;

    public ImageConversionService(TextColorSchemaService colorSchema,
                                  TextGraphicsConverterService converterService) {
        this.colorSchema = colorSchema;
        this.converterService = converterService;
    }

    public String convertToAscii(byte[] file) throws Exception {
        var result = converterService.convert(file);
        return result;
    }
}
