package com.sya.engine;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.VelocityTracker;
import com.sya.engine.drawable.sprites.Ball;
import com.sya.engine.drawable.sprites.Creator;
import com.sya.engine.drawable.sprites.Player;
import com.sya.engine.drawable.SpriteObject;
import com.sya.engine.util.Collision;

/*
    created by Farhad Mohammad 2019 All rights reserved, TechCompetence/SYA.
 */

class GameView extends SurfaceView implements SurfaceHolder.Callback {

    //Vars
    public MainThread thread;
    public SpriteObject player;
    public SpriteObject enemy;
    public SpriteObject ball;
    public SpriteObject creator;
    public boolean showCreator = false;
    private VelocityTracker mVelocityTracker = null;


    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerID = event.getPointerId(index);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if(mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(event);
                break;
                case MotionEvent.ACTION_MOVE:
                    mVelocityTracker.addMovement(event);
                    mVelocityTracker.computeCurrentVelocity(1000);
                    player.setLocation(mVelocityTracker.getXVelocity(), 1750);
                    break;
        }
        System.out.println("Player Location: " + event.getX() + " y: " + event.getY());
        return super.onTouchEvent(event);
    }
    @Override
    public boolean onCapturedPointerEvent(MotionEvent motionEvent) {
        // Get the coordinates required by your app
        player.setLocation(motionEvent.getX(), 1750);
        // Use the coordinates to update your view and return true if the event was
        // successfully processed
        return true;
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.player), 100, 1750);
        enemy = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.player), 44, 46);
        ball = new Ball(BitmapFactory.decodeResource(getResources(), R.drawable.ball), 500, 1000);
        creator = new Creator("Game created by Farhad", 100, 100);
        //start the thread
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        if (Collision.isCollisionDetected(ball.getBitmap(), ball.getX(), ball.getY(), enemy.getBitmap(), enemy.getX(), enemy.getY())) {
            System.out.println("Collision detected between player and ball");
            showCreator = !showCreator;
            ball.reverseY();
            ball.setX(ball.getX() + 30);
            ball.reverseX();
        }
        ball.update();
        enemy.setX(ball.getX());
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            player.drawSprite(canvas);
            enemy.drawSprite(canvas);
            ball.drawSprite(canvas, 50, 50);
            if (showCreator) {
                creator.drawText(canvas, 255, 0, 0, 45);
            }
        }
    }
}
