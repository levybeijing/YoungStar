package com.chuanqing.youngstar.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CircleBarView extends View {

    private Paint progressPaint;
    private int mProgress=90;

    public CircleBarView(Context context) {
        super(context);
    }

    public CircleBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        progressPaint = new Paint();
        progressPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        progressPaint.setColor(Color.RED);
        progressPaint.setStrokeWidth(20);
        progressPaint.setAntiAlias(true);//设置抗锯齿
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = 50;
        float y = 50;
        RectF rectF = new RectF(x,y,x+500,y+500);//建一个大小为300 * 300的正方形区域

        canvas.drawArc(rectF,270,mProgress,false,progressPaint);//这里角度0对应的是三点钟方向，顺时针方向递增
    }

    public void setProgress(int progress) {
        mProgress = (int) (progress*3.6f);
        postInvalidate();//重绘
    }
}
