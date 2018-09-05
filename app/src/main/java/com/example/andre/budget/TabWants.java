package com.example.andre.budget;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.graphics.drawable.TransitionDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class TabWants extends Fragment {
    private BottomSheetBehavior mBottomSheetBehavior;
    private TextView mTextViewState;
    View bottomSheet;
    View rectangleBar;
    CircularProgressBar circularProgressBar;

    private GestureDetector mDetector;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabwants, container, false);

        bottomSheet = rootView.findViewById(R.id.bottom_sheet);
        rectangleBar = rootView.findViewById(R.id.myRectangleView2);


        //Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        mDetector = new GestureDetector(getContext(), new TabWants.MyGestureListener());

        rootView.setOnTouchListener(touchListener);
        circularProgressBar = (CircularProgressBar)rootView.findViewById(R.id.flexBar);
        int animationDuration = 2500; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation(60, animationDuration);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        int colorTo = getResources().getColor(R.color.grey_light);
                        int colorFrom = getResources().getColor(R.color.grey_dark);
                        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
                        colorAnimation.setDuration(250); // milliseconds
                        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                            @Override
                            public void onAnimationUpdate(ValueAnimator animator) {
                                rectangleBar.setBackgroundColor((int) animator.getAnimatedValue());
                            }

                        });
                        colorAnimation.start();
                        //moveUpDown(Tabbed.toolbar,"DOWN");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        //moveUpDown(Tabbed.toolbar,"UP");
                        colorFrom = getResources().getColor(R.color.grey_light);
                        colorTo = getResources().getColor(R.color.grey_dark);
                        colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
                        colorAnimation.setDuration(100); // milliseconds
                        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                            @Override
                            public void onAnimationUpdate(ValueAnimator animator) {
                                rectangleBar.setBackgroundColor((int) animator.getAnimatedValue());
                            }

                        });

                        ValueAnimator va = ValueAnimator.ofInt(rectangleBar.getMeasuredHeight(), rectangleBar.getMeasuredHeight() + 90);
                        va.setDuration(400);
                        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public void onAnimationUpdate(ValueAnimator animation) {
                                Integer value = (Integer) animation.getAnimatedValue();
                                rectangleBar.getLayoutParams().height = value.intValue();
                                rectangleBar.requestLayout();
                            }
                        });

                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.play(va).with(colorAnimation);

                        animatorSet.start();

                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        return rootView;
    }
    // This touch listener passes everything on to the gesture detector.
    // That saves us the trouble of interpreting the raw touch events
    // ourselves.
    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // pass the events to the gesture detector
            // a return value of true means the detector is handling it
            // a return value of false means the detector didn't
            // recognize the event
            return mDetector.onTouchEvent(event);

        }
    };
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent event) {
            Log.d("TAG","onDown: ");

            // don't return false here or else none of the other
            // gestures will work

            //mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("TAG", "onSingleTapConfirmed: ");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("TAG", "onLongPress: ");
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("TAG", "onDoubleTap: ");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.i("TAG", "onScroll: ");
            return true;
        }
        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_MAX_OFF_PATH = 250;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {

            if (e1.getY()-e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY)
            {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }


            return true;
        }
    }
    public static void moveUpDown(final Toolbar view, String upDown){
        Integer finalHeight = view.getMeasuredHeight();
        final AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) view.getLayoutParams();
        //final NestedScrollView.LayoutParams params2 = (NestedScrollView.LayoutParams) bottomSheet.getLayoutParams();
        ValueAnimator animator;
        //ValueAnimator animator2;
        if(upDown.equals("UP")) {
            animator = ValueAnimator.ofInt(params.topMargin, -finalHeight);
        }
        else {
            animator = ValueAnimator.ofInt(params.topMargin, 0);
        }
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                params.topMargin = (Integer) valueAnimator.getAnimatedValue();

                //params2.topMargin = (Integer) valueAnimator.getAnimatedValue();
                view.requestLayout();
            }
        });
        animator.setDuration(600);
        animator.start();
    }
}
