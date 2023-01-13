package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class Shape {
    protected int x;
    protected int y;
    protected int width;
    protected String color;
    protected boolean style;


    public Shape(int x, int y, String color,boolean style,int width) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.style=style;
        this.width=width;
    }

    public abstract void updatePoint(int xe,int ye);

    public void draw(Canvas canvas, Paint paint)
    {
        paint.setStrokeWidth(this.width);
        paint.setColor(Color.parseColor(color));
        if(this.style){
            paint.setStyle(Paint.Style.STROKE);
        }
        else {
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        }

    }
}