package com.example.paint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
public class Area extends Shape {
    private int xEnd;
    private int yEnd;
    private int area;
    public Area(int x, int y, String color, boolean style, int width) {
        super(x, y, color, style, width);


    }

    public float getArea() {
        return area;
    }

    @Override
    public void updatePoint(int xe, int ye){

    }



}
