package com.sya.engine.drawable.sprites;

import android.graphics.Bitmap;
import com.sya.engine.drawable.SpriteObject;

public class Enemy extends SpriteObject {

    private int x;
    private int y;
    private Bitmap image;


    public Enemy(Bitmap bmp, int x, int y) {
        super(bmp, x, y);
        image = bmp;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {

    }


}
