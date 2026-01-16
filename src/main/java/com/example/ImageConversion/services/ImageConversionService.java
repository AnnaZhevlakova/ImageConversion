package com.example.ImageConversion.services;

import com.example.ImageConversion.dto.ImageParams;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("request")
@Service
public class ImageConversionService {
    private TextColorSchemaService colorSchema;
    private TextGraphicsConverterService converterService;
    private ImageParams imageParams;

    public ImageConversionService(TextColorSchemaService colorSchema,
                                  TextGraphicsConverterService converterService) {
        this.colorSchema = colorSchema;
        this.converterService = converterService;
    }

    public String convertToAscii(byte[] file, ImageParams imageParams) throws Exception {
        var result = converterService.convert(file, imageParams);
        return result;
    }
}
