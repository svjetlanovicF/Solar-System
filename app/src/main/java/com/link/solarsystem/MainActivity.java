package com.link.solarsystem;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.IpSecManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    Bitmap bitmap;
    Canvas canvas;
    Paint paint;
    ImageView imageView;
    Timer timer;

    int[] screenDimension;

    float sunX, sunY, sunRadius;

    float mercurX, mercurY, mercurRadius;

    float veneraX, veneraY, veneraRadius;

    Canvas mercur, venera, earth;
    Paint mercurP, veneraP, earthP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenDimension = getDisplayDimension();

        bitmap = Bitmap.createBitmap(screenDimension[0], screenDimension[1], Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(bitmap);

        mercur = new Canvas();
        mercur.setBitmap(bitmap);

        venera = new Canvas();
        venera.setBitmap(bitmap);

        earth = new Canvas();
        earth.setBitmap(bitmap);

        paint = new Paint();
        mercurP = new Paint();
        veneraP = new Paint();
        earthP = new Paint();
        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);

        sunX = (float)screenDimension[0]/2;
        sunY = (float)screenDimension[1]/2;
        sunRadius = 120;

        mercurX = (float)screenDimension[0] /2;
        mercurY = (float)screenDimension[1] /3;
        mercurRadius = 70;

        veneraX = (float)screenDimension[0] /2;
        veneraY = (float)screenDimension[1] /4;
        veneraRadius = 50;
        

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        renderFrame();
                    }
                });
            }
        }, 0, 400);

    }

    private int[] getDisplayDimension(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        return new int[]{width, height};
    }

    public void renderFrame(){

        canvas.drawColor(Color.parseColor("#EBEBEB"));
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(sunX, sunY, sunRadius, paint);

        mercurP.setColor(Color.RED);
        mercur.drawCircle(mercurX, mercurY, mercurRadius, mercurP);
        mercur.rotate(30, screenDimension[0] / 2, screenDimension[1] / 2);

        veneraP.setColor(Color.BLACK);
        venera.drawCircle(veneraX, veneraY, veneraRadius, veneraP);
        venera.rotate(-50, screenDimension[0] / 2, screenDimension[1] / 2);

        imageView.invalidate();
    }

}