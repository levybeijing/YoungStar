package com.chuanqing.youngstar.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.chuanqing.youngstar.tools.DpOrPxUtils;

public class WaveProgressView extends View {
    private Paint wavePaint;//绘制波浪画笔
    private Path wavePath;//绘制波浪Path

    private float waveWidth;//波浪宽度
    private float waveHeight;//波浪高度

    public WaveProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        waveWidth = DpOrPxUtils.dip2px(context,15);
        waveHeight = DpOrPxUtils.dip2px(context,20);

        wavePath = new Path();

        wavePaint = new Paint();
        wavePaint.setColor(Color.RED);
        wavePaint.setAntiAlias(true);//设置抗锯齿
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(getWavePath(),wavePaint);
    }

    private Path getWavePath(){
        wavePath.reset();
        wavePath.moveTo(0,waveHeight);//起始点移动至(0,waveHeight),注意坐标系y轴是向下的
        for (int i=0;i<5;i++){
            wavePath.rQuadTo(waveWidth/2, waveHeight, waveWidth, 0);
            wavePath.rQuadTo(waveWidth/2, -waveHeight, waveWidth, 0);
        }
        return wavePath;
    }

}
