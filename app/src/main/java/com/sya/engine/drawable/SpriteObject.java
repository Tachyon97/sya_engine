package com.sya.engine.drawable;

import android.content.res.Resources;
import android.graphics.*;


public abstract class SpriteObject {
    private float positionX, positionY;
    private String text;

    private Bitmap resized;
    private int xVelocity;
    private int yVelocity;

    public SpriteObject(Bitmap bmp, int x, int y) {
        resized = bmp;
        this.positionX = x;
        this.positionY = y;
    }

    public SpriteObject(String text, int x, int y) {
        this.positionX = x;
        this.positionY = y;
        this.text = text;
    }

    public SpriteObject(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public void drawSprite(Canvas canvas, int width, int height) {
        resized = resize(resized, width, height);
        canvas.drawBitmap(resized, positionX, positionY, null);
    }

    public void drawSprite(Canvas canvas) {
        canvas.drawBitmap(resized, positionX, positionY, null);
    }

    public void drawCircle(Canvas canvas, int radius) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(255, 0, 0));
        canvas.drawCircle((float) positionX, (float) positionY, radius, paint);
    }

    public void drawText(Canvas canvas, int red, int green, int blue, int size) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(red, green, blue));
        paint.setTextSize(size);
        canvas.drawText(text, positionX, positionY, paint);
    }

    public Bitmap resize(Bitmap img, int height, int width) {
        int w = img.getWidth();
        int h = img.getHeight();
        float scaleW = ((float) width / w);
        float scaleH = ((float) height / h);
        Matrix matrix = new Matrix();
        matrix.postScale(scaleW, scaleH);
        Bitmap resizedBmp = Bitmap.createBitmap(img, 0, 0, w, h, matrix, false);
        return resizedBmp;
    }

    public abstract void update();

    public Bitmap getBitmap() {
        return resized;
    }

    public int getBitmapWidth() {
        return resized.getWidth();
    }

    public int getBitmapHeight() {
        return resized.getHeight();
    }


    public int getX() {
        return (int) positionX;
    }

    public int getY() {
        return (int) positionY;
    }

    public void setX(float x) {
        positionX = x;
    }

    public void setY(float y) {
        positionY = y;
    }

    public void setLocation(int x, int y) {
        positionX = x;
        positionY = y;
    }

    public void setLocation(float x, float y) {
        positionX = x;
        positionY = y;
    }
    public float reverseX() {
        return xVelocity = xVelocity * -1;
    }
    public float reverseY() {
        return yVelocity = yVelocity * -1;
    }
}
