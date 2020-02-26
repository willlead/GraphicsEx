package com.example.graphicsex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new MyCanvas(this));
    }

    private static class MyCanvas extends View{


        public MyCanvas(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint p = new Paint();
            p.setAntiAlias(true); // antialias 끝을 부드럽게 둥글게 해준다.
            /*p.setColor(Color.BLUE);
            canvas.drawLine(10,10,300,10, p);

            p.setColor(Color.RED);
            p.setStrokeWidth(5); // 붓의 굵기
            canvas.drawLine(10,30,300,30, p);

            p.setColor(Color.RED);
            p.setStrokeWidth(0);

            p.setStyle(Paint.Style.FILL);
            Rect r1 = new Rect(10,50,10+100,50+100);
            canvas.drawRect(r1, p);

            p.setStyle(Paint.Style.STROKE);
            Rect r2 = new Rect(130, 50, 130+100, 50+100);
            canvas.drawRect(r2, p);

            RectF r3 = new RectF(250, 50, 250+100, 50+100);
            canvas.drawRoundRect(r3, 20, 20, p);

            p.setStrokeWidth(5);
            Path path1 = new Path();
            path1.moveTo(10, 290);
            path1.lineTo(10+ 50, 290+50);
            path1.lineTo(10+100, 290);
            path1.lineTo(10+150, 290+50);
            path1.lineTo(10+200, 290);
            canvas.drawPath(path1, p);

            p.setStrokeWidth(0);
            p.setTextSize(30);
            canvas.drawText("안드로이드", 10, 390, p);*/

            p.setStrokeWidth(30);
            canvas.drawLine(30,30,300,30, p);

            p.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawLine(30,90,300,90, p);

            RectF rectF = new RectF();
            rectF.set(60, 120, 260, 200);
            canvas.drawOval(rectF, p);

            rectF.set(60, 170, 100, 270);
            canvas.drawArc(rectF, 40, 110, true, p);

            p.setColor(Color.BLUE);
            rectF.set(60, 280, 160, 380);
            canvas.drawRect(rectF, p);

            p.setColor(Color.argb(255, 255,0,0));
            rectF.set(90, 310, 190, 410);
            canvas.drawRect(rectF, p);


        }
    }
}
