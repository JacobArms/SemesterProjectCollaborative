package com.example.monkeyrun;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SwipeListener extends SurfaceView implements OnTouchListener {
    GestureListener gestureListener = new GestureListener();
    private final GestureDetector gestureDetector;
    public SwipeListener (Context ctx){
        super(ctx);
        gestureDetector = new GestureDetector(ctx, gestureListener);
    }

    public void onSwipeLeft() {
        Log.println(Log.ASSERT, "SAUER", "SWIPE LEFT");
    }

    public void onSwipeRight() {
        Log.println(Log.ASSERT, "SAUER", "SWIPE LEFT");

    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }


    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0)
                    onSwipeRight();
                else
                    onSwipeLeft();
                return true;
            }
            return false;
        }
    }


//
//    SwipeListener(View view, Aiai aiai, int screenX) {
//        int threshold = 100;
//        int velocityThreshhold = 100;
//
//        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
//
//            public boolean onDown(MotionEvent e) {
//                return true;
//            }
//
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                float xDiff = e2.getX() - e1.getX();
//                float yDiff = e2.getY() - e1.getY();
//                try {
//                    if (Math.abs(xDiff) > Math.abs(yDiff)) {
//                        if (Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocityThreshhold) {
//                            if (xDiff > 0) {
//                                //right
//                                aiai.x += screenX/5;
//
//                            } else {
//                                //left
//                                aiai.x -= screenX/5;
//                            }
//                            return true;
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return false;
//            }
//        };
//        gestureDetector = new GestureDetector(listener);
//        view.setOnTouchListener(this);
//    }
}
