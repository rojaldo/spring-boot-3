package com.example.demo;

public class Samples {

    Samples() {
        // TODO Auto-generated constructor stub
    }

    public float getArea(float length, float width) {
        if (length < 0 || width < 0) {
            throw new IllegalArgumentException("Negative sizes are not allowed");
        }else if(length == 0 || width == 0){
            throw new IllegalArgumentException("Zero sizes are not allowed");
        }else if (length > 10000 || width > 10000) {
            throw new IllegalArgumentException("Sizes greater than 10000 are not allowed");
        }
        return length * width;
    }

    public int getPerimeter(int length, int width) {
        return 2 * (length + width);
    }
    
}
