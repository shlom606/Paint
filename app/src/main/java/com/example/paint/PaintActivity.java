package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class PaintActivity extends AppCompatActivity {
    private static final String TAG = "PaintActivity";
    private FrameLayout frame;
    private PaintView paintView;
    ImageView imgView;
    Bitmap bitmap;
    int r,g,b,width=15;
    int pixels;
    Button plus,minus,keepmax;
    TextView widthNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        plus=findViewById(R.id.btnplus);
        minus=findViewById(R.id.btnminus);
        keepmax=findViewById(R.id.keepmax);
        imgView=findViewById(R.id.colorpickers);
        imgView.setDrawingCacheEnabled(true);
        imgView.buildDrawingCache(true);
        frame = findViewById(R.id.frm);
        widthNum=findViewById(R.id.widthpaint);
        paintView = new PaintView(this);
        frame.addView(paintView);
        imgView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN||event.getAction() == MotionEvent.ACTION_MOVE){
                    bitmap=imgView.getDrawingCache();
                    pixels=bitmap.getPixel((int)event.getX(),(int)event.getY());
                    r= Color.red(pixels);
                    b= Color.blue(pixels);
                    g= Color.green(pixels);
                    String hex="#"+Integer.toHexString(pixels);
                    paintView.setColor(hex);


                }

                return true;
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                width+=1;
                paintView.ChangeWidth(width);
                widthNum.setText(String.valueOf(width));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                width-=1;
                paintView.ChangeWidth(width);
                widthNum.setText(String.valueOf(width));
            }
        });
        keepmax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.deleteNonMax();
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.nev_menu,menu);

        return true;
    }
        public boolean onOptionsItemSelected(MenuItem item){
            super.onOptionsItemSelected(item);
            int id= item.getItemId();
            if(id==R.id.Stroke){
                paintView.setStyle(true);
            }
            if(id==R.id.Fill){
                paintView.setStyle(false);
            }
            if(id==R.id.HidePalatte){
                Hide(false);
            }
            if(id==R.id.ShowPalatte){
                Hide(true);
            }
            return true;
        }

    public void addLine(View view) {
        paintView.addLine();
    }
    public void addRect(View view) {
        paintView.addRect();
    }
    public void addPath(View view) {
        paintView.addPath();
    }
    public void addCircle(View view) {
        paintView.addCircle();
    }


    public void changeColor(View view)
    {
        String color = view.getTag().toString();
        paintView.setColor(color);
    }
    
    public void clear(View view) {
        paintView.undo();
    }
    public void Hide(boolean show){
        if(!show)
            imgView.setVisibility(View.INVISIBLE);
        else
            imgView.setVisibility(View.VISIBLE);
    }

}
