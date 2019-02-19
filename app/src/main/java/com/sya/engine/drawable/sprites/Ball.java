package com.sya.engine.drawable.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.sya.engine.drawable.SpriteObject;

public class Ball extends SpriteObject {
    private int x;
    private int y;
    private int xVelocity = 25;
    private int yVelocity = 20;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private Bitmap image;

    public Ball(Bitmap bmp, int x, int y) {
        super(bmp, x, y);
        this.x = x;
        this.y = y;
        image = bmp;
    }

    @Override
    public void drawSprite(Canvas canvas, int width, int height) {
        image = resize(image, width, height);
        canvas.drawBitmap(image, x, y, null);
    }

    @Override
    public void update() {
        x += xVelocity;
        y += yVelocity;
        if ((x > screenWidth - image.getWidth()) || (x < 0)) {
            reverseX();
        }
        if ((y > screenHeight - image.getHeight()) || (y < 0)) {
            reverseY();
        }
    }

    @Override
    public float reverseX() {
        return xVelocity = xVelocity * -1;
    }
    @Override
    public float reverseY() {
        return yVelocity = yVelocity * -1;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
