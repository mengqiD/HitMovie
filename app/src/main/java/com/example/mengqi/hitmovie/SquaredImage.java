package com.example.mengqi.hitmovie;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

final class SquaredImage extends ImageView {
    public SquaredImage(Context context) {
        super(context);
    }

    public SquaredImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}

