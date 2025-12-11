package com.example.ImageConversion.dto;

public class FileResponse {
    private String result;

    public FileResponse(){

    }

    public FileResponse(String result){
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
