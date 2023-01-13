package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Stack;

public class PaintView extends View {


    private Paint paint;
    private Paint bgPaint;
    private String currentShape = "Rect";
    private String currentColor = "#000000";
    private Stack<Shape> shapes;


    public PaintView(Context context) {
        super(context);

        shapes = new Stack<>();
        paint = new Paint();

        bgPaint = new Paint();
        paint.setColor(Color.RED);

        bgPaint.setColor(Color.rgb(255,255,255));


    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(bgPaint);
        if(shapes.size()!=0){
            for (int i = 0; i < shapes.size(); i++)
                shapes.get(i).draw(canvas, paint);
        }

    }

    Shape shape;
    int currntWidth=15;
    boolean currntstyle;
    float maxArea=0,area;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {

            if(currentShape.equals("Rect"))
            {
                shape = new RectShape((int)event.getX(),(int)event.getY(),currentColor,currntstyle,currntWidth);
            }
            if(currentShape.equals("Line")){
                shape=new LineShape((int)event.getX(),(int)event.getY(),currentColor,true,currntWidth);
            }
            if(currentShape.equals("Circle")){
                shape=new CircleShape((int)event.getX(),(int)event.getY(),currentColor,currntstyle,currntWidth);
            }
            if(currentShape.equals("Path")){
                shape=new PathShape((int)event.getX(),(int)event.getY(),currentColor,true,currntWidth);
            }

            if(shape instanceof Area){
                if(SingleArea(shape)>=maxArea){
                    maxArea=SingleArea(shape);
                }
            }

            shapes.push(shape);

            invalidate();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            shape.updatePoint((int)event.getX(),(int)event.getY());
            invalidate();
        }
        return true;
    }

    public void ChangeWidth(int width){
        currntWidth=width;
    }
    public float SingleArea(Shape shape){

        if(shape instanceof Area)
            return ((Area) shape).getArea();
        return 0;
    }
    public void deleteNonMax(){
        Shape curShape,maxShape = null;
        int fulsize=shapes.size();
        while (shapes.size()!=0){
            curShape=shapes.pop();
            invalidate();
            if(SingleArea(curShape)>maxArea){
                maxShape=curShape;
                maxArea=SingleArea(curShape);
            }

        }
        shapes.push(maxShape);
        invalidate();

    }

    public void addLine() {
        currentShape = "Line";
    }

    public void addRect() {
        currentShape = "Rect";
    }

    public void addCircle() {
        currentShape = "Circle";
    }
    public void addPath() {
        currentShape = "Path";
    }

    public void setColor(String color)
    {
        currentColor = color;
    }
    public void ChangeStyle(boolean style){
        if(style){
            currntstyle=false;
        }
        if(!style){
            currntstyle=true;
        }
    }
    public void setStyle(boolean style){
        currntstyle=style;
    }
    public void undo()
    {
        shapes.pop();
        invalidate();
    }
}
