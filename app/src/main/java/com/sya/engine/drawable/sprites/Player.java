package com.sya.engine.drawable.sprites;

import android.graphics.Bitmap;
import com.sya.engine.drawable.SpriteObject;

public class Player extends SpriteObject {
    private int x;
    private int y;
    private int vela = 10;
    private int velab = 10;
    public Player(Bitmap bmp, int x, int y) {
        super(bmp, x, y);
        this.x = x;
        this.y = y;
    }


    @Override
    public void update() {
    }
}
