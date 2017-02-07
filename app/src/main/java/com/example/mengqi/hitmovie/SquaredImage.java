package com.example.mengqi.hitmovie;

/**
 * Created by Mengqi on 2/7/17.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * An image view which always remains square with respect to its width.
 */
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

