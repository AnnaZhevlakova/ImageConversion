package com.example.ImageConversion.services;


public class TextColorSchemaService {
    private static final char[] ASCII_CHARS = {'#', '$', '@', '%', '*', '+', '-', '\''};

    public char convert(int color) {
        int index = (int) ((color / 255.0) * (ASCII_CHARS.length - 1));
        return ASCII_CHARS[index];

    }
}
