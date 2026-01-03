package com.example.ImageConversion.dto;

public class ImageParams {
    private int maxWidth;
    private int maxHeight;
    private double maxRatio;

    public ImageParams() {
        maxWidth = -1;
        maxHeight = -1;
        maxRatio = -1;
    }
    public ImageParams(int maxWidth,int maxHeight,double maxRatio){
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.maxRatio = maxRatio;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public double getMaxRatio() {
        return maxRatio;
    }

    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }
}
