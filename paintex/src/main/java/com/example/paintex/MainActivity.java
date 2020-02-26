package com.example.paintex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final static int LINE = 1, CIRCLE= 2, RECTANGLE = 3;
    final static int RED = 4, GREEN = 5, BLUE = 6, DEFAULT = 7;
    static int curShape = LINE;
    static int curColor = Color.BLACK;
    static List<MyShape> myShapes = new ArrayList<MyShape>();
    static boolean isFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new MyCanvas(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, LINE, 0,"선그리기");
        menu.add(0,CIRCLE,0,"원그리기");
        menu.add(0, RECTANGLE, 0, "사각형그리기");
        SubMenu subMenu = menu.addSubMenu("색상 변경>>");
        subMenu.add(0, RED, 0, "빨강");
        subMenu.add(0, GREEN, 0, "초록");
        subMenu.add(0, BLUE, 0, "파랑");
        subMenu.add(0, DEFAULT, 0, "기본");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case LINE: //선그리기
                curShape = LINE; break;
            case CIRCLE: //원그리기
                curShape = CIRCLE; break;
            case RECTANGLE:
                curShape = RECTANGLE; break;
            case BLUE:
                curColor = Color.BLUE; break;
            case RED:
                curColor = Color.RED; break;
            case GREEN:
                curColor = Color.GREEN; break;
            case DEFAULT:
                curColor = Color.BLACK; break;

        }

        return super.onOptionsItemSelected(item);
    }

    private static class MyCanvas extends View{

        int startX = -1, startY = -1, stopX = -1, stopY = -1;

        public MyCanvas(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    /*Log.i("test","좌표값 x= "+event.getX());
                    Log.i("test","좌표값 y= "+event.getY());*/
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    isFinish = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate(); // onDraw메서드를 호출한다. 무효화시킨다? 새도화지를 호출한다.
                    break;
                case MotionEvent.ACTION_UP:
                    isFinish = true;
                    MyShape shape = new MyShape();
                    shape.shapeType = curShape;
                    shape.color = curColor;
                    shape.startX = startX;
                    shape.startY = startY;
                    shape.stopX = stopX;
                    shape.stopY = stopY;
                    myShapes.add(shape);
                    this.invalidate();
                    break;
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);

            for (int i =0; i<myShapes.size(); i++)
            {
                MyShape shape = myShapes.get(i);
                paint.setColor(shape.color);
                switch (shape.shapeType)
                {
                    case LINE:
                        canvas.drawLine(shape.startX, shape.startY, shape.stopX, shape.stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(shape.stopX - shape.startX, 2)
                                + Math.pow(shape.stopY - shape.startY, 2));
                        canvas.drawCircle(shape.startX, shape.startY, radius, paint);
                        break;
                    case RECTANGLE:
                        Rect rect = new Rect(shape.startX, shape.startY, shape.stopX, shape.stopY);
                        canvas.drawRect(rect, paint);
                        break;
                }
            }

            if(!isFinish)
            {
                paint.setColor(curColor);
                switch (curShape)
                {
                    case LINE:
                        canvas.drawLine(startX, startY, stopX, stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY, 2));
                        canvas.drawCircle(startX, startY, radius, paint);
                        break;
                    case RECTANGLE:
                        Rect rect = new Rect(startX, startY, stopX, stopY);
                        canvas.drawRect(rect, paint);
                        break;
                }
            }
        }
    }

    private static class MyShape
    {
        int shapeType, color;// 도형의 종류
        int startX, startY, stopX, stopY;
    }

}
