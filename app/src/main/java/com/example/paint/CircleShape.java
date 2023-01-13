package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CircleShape extends Area {

    private int xEnd;
    private int yEnd;
    private float radius;
    public CircleShape(int x, int y, String color,boolean style,int width) {
        super(x, y, color,style,width);
        xEnd = x;
        yEnd = y;
    }
    @Override
    public float getArea() {
        radius= (float) Math.sqrt(Math.pow(xEnd-x,2)+Math.pow(yEnd-y,2))/2;
        return (float) (Math.pow(radius,2)*Math.PI);
    }


    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        canvas.drawCircle(x,y, (float) Math.sqrt(Math.pow(xEnd-x,2)+Math.pow(yEnd-y,2)),paint);
    }
}