package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class PathShape extends Shape {

    private int xEnd;
    private int yEnd;
    Path path;

    public PathShape(int x, int y, String color,boolean style,int width) {
        super(x, y, color,style,width);
        xEnd = x;
        yEnd = y;
        path=new Path();
        path.moveTo(xEnd,yEnd);
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
        path.lineTo(xe,ye);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        canvas.drawPath(path,paint);
    }
}