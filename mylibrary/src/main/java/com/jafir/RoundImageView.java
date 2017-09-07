package com.jafir;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


public class RoundImageView extends ImageView {
    private int mInsideBorderThickness ;
    private int mOutsideBorderThickness ;
    private int mBorderOutsideColor ;
    private int mBorderInsideColor ;
    private Bitmap currentBitmap;

    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context.obtainStyledAttributes(attrs, R.styleable.RoundImageView));
    }

    private void initAttrs(TypedArray typedArray) {
        mBorderInsideColor = typedArray.getColor(R.styleable.RoundImageView_inside_border_color, 0);
        mBorderOutsideColor = typedArray.getColor(R.styleable.RoundImageView_outside_border_color, 0);
        mInsideBorderThickness = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_inside_border_width, 0);
        mOutsideBorderThickness = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_outside_border_width, 0);
    }

    private void initCurrentBitmap() {
        Bitmap temp = null;
        Drawable drawable = getDrawable();
        if (drawable instanceof BitmapDrawable) {
            temp = ((BitmapDrawable) drawable).getBitmap();
        }
        if (temp != null) {
            currentBitmap = temp;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initCurrentBitmap();
        if (currentBitmap == null || getWidth() == 0 || getHeight() == 0) {
            return;
        }
        this.measure(0, 0);
        int width = getWidth();
        int height = getHeight();
        int radius = 0;
        if (mBorderInsideColor != 0 && mBorderOutsideColor != 0) { // draw two
            radius = (width < height ? width : height) / 2 - mInsideBorderThickness - mOutsideBorderThickness;
            //draw inside
            drawCircleBorder(canvas, radius + mInsideBorderThickness / 2, width,
                    height, mBorderInsideColor, mInsideBorderThickness);
            //draw outside
            drawCircleBorder(canvas, radius + mInsideBorderThickness
                    + mOutsideBorderThickness / 2, width, height, mBorderOutsideColor, mOutsideBorderThickness);
        } else if (mBorderInsideColor != 0) { // draw inside
            radius = (width < height ? width : height) / 2 - mInsideBorderThickness;
            drawCircleBorder(canvas, radius + mInsideBorderThickness / 2, width,
                    height, mBorderInsideColor, mInsideBorderThickness);
        } else if (mBorderOutsideColor != 0) {// draw outside
            radius = (width < height ? width : height) / 2 - mOutsideBorderThickness;
            drawCircleBorder(canvas, radius + mOutsideBorderThickness / 2, width,
                    height, mBorderOutsideColor, mOutsideBorderThickness);
        } else { // draw no border
            radius = (width < height ? width : height) / 2;
        }
        Bitmap roundBitmap = getCroppedRoundBitmap(currentBitmap, radius);
        canvas.drawBitmap(roundBitmap, width / 2 - radius, height / 2 - radius,
                null);
    }

    public Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {
        Bitmap scaledSrcBmp;
        int diameter = radius * 2;
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        int squareWidth = 0, squareHeight = 0;
        int x = 0, y = 0;
        Bitmap squareBitmap;
        if (bmpHeight > bmpWidth) {
            squareWidth = squareHeight = bmpWidth;
            x = 0;
            y = (bmpHeight - bmpWidth) / 2;
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);
        } else if (bmpHeight < bmpWidth) {
            squareWidth = squareHeight = bmpHeight;
            x = (bmpWidth - bmpHeight) / 2;
            y = 0;
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);
        } else {
            squareBitmap = bmp;
        }

        if (squareBitmap.getWidth() != diameter
                || squareBitmap.getHeight() != diameter) {
            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter,
                    diameter, true);

        } else {
            scaledSrcBmp = squareBitmap;
        }
        Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(scaledSrcBmp.getWidth() / 2,
                scaledSrcBmp.getHeight() / 2, scaledSrcBmp.getWidth() / 2,
                paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);
        // bmp.recycle();
        // squareBitmap.recycle();
        // scaledSrcBmp.recycle();
        bmp = null;
        squareBitmap = null;
        scaledSrcBmp = null;
        return output;
    }

    /**
     * 边缘画圆
     */
    private void drawCircleBorder(Canvas canvas, int radius, int w, int h,
                                  int color, int width) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(width);
        canvas.drawCircle(w / 2, h / 2, radius, paint);
    }

    public void setBitmapRes(Bitmap bitmap) {
        currentBitmap = bitmap;
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        setBitmapRes(bm);
        super.setImageBitmap(bm);
    }

    @Override
    public void setImageResource(int resId) {
        setBitmapRes(BitmapFactory.decodeResource(getResources(), resId));
        super.setImageResource(resId);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        if(drawable !=null) {
            setBitmapRes(((BitmapDrawable) drawable).getBitmap());
        }
        super.setImageDrawable(drawable);
    }

    public int getmInsideBorderThickness() {
        return mInsideBorderThickness;
    }

    public void setmInsideBorderThickness(int mInsideBorderThickness) {
        this.mInsideBorderThickness = mInsideBorderThickness;
    }

    public int getmOutsideBorderThickness() {
        return mOutsideBorderThickness;
    }

    public void setmOutsideBorderThickness(int mOutsideBorderThickness) {
        this.mOutsideBorderThickness = mOutsideBorderThickness;
    }

    public int getBorderOutsideColor() {
        return mBorderOutsideColor;
    }

    public void setBorderOutsideColor(int borderOutsideColor) {
        this.mBorderOutsideColor = borderOutsideColor;
    }

    public int getBorderInsideColor() {
        return mBorderInsideColor;
    }

    public void setBorderInsideColor(int borderInsideColor) {
        this.mBorderInsideColor = borderInsideColor;
    }
}