package com.example.hwunit8;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class graph extends View {
    Map<String, Float> data = new HashMap<>();
    private Paint mPaint;
    private float barWidth= 60f;
    private float barSpace = 40f;
    int count =0;
    public graph(Context context) {
        super(context);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.BLUE);
        float sw = canvas.getWidth();
        float sh = canvas.getHeight();
        canvas.drawARGB(255,255,255,0);

        canvas.drawLine(20,sh*7/8f,sw-20,sh*7/8f, mPaint);
        canvas.drawLine(sw*1/8f,0,sw*1/8f,sh,mPaint);
        mPaint.setTextSize(50);
        data.forEach((key, value)->{
            mPaint.setColor(Color.RED);
            canvas.drawRect(sw/8+count*(barWidth+barSpace),sh*(7/8f-(float) value),sw/8 + barWidth+count*(barWidth+barSpace),sh*7/8f,mPaint);
            mPaint.setColor(Color.GREEN);
            canvas.drawText(key,sw/8+15+count*(barWidth+barSpace),7*sh/8+80,mPaint );
            count++;
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getValues(Map<String, Float> values){
        values.forEach((key,value)->{
            //Log.d("key",value.toString());
            data.put(key,value);
        });
    }
}