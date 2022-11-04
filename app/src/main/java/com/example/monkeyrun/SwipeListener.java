package com.example.monkeyrun;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class SwipeListener implements View.OnTouchListener {
    GestureDetector gestureDetector;

    SwipeListener(View view, Aiai aiai, int screenX) {
        int threshold = 100;
        int velocityThreshhold = 100;

        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {

            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float xDiff = e2.getX() - e1.getX();
                float yDiff = e2.getY() - e1.getY();
                try {
                    if (Math.abs(xDiff) > Math.abs(yDiff)) {
                        if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocityThreshhold) {
                            if (xDiff > 0) {
                                //right
                                aiai.x += screenX/5;

                            } else {
                                //left
                                aiai.x -= screenX/5;
                            }
                            return true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        };
        gestureDetector = new GestureDetector(listener);
        view.setOnTouchListener(this);
    }
    public boolean onTouch(View view, MotionEvent motionEvent){
        return gestureDetector.onTouchEvent(motionEvent);
    }
}
