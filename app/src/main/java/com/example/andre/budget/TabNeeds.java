package com.example.andre.budget;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class TabNeeds extends Fragment {

    private BottomSheetBehavior mBottomSheetBehavior;
    private TextView mTextViewState;
    CircularProgressBar circularProgressBar1;

    private GestureDetector mDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabneeds, container, false);

        View bottomSheet = rootView.findViewById(R.id.bottom_sheet);
        //Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        mDetector = new GestureDetector(getContext(), new MyGestureListener());

        rootView.setOnTouchListener(touchListener);

        mTextViewState = rootView.findViewById(R.id.text_view_state);


        circularProgressBar1 = (CircularProgressBar)rootView.findViewById(R.id.needsBar);

        int animationDuration = 2500; // 2500ms = 2,5s
        circularProgressBar1.setProgressWithAnimation(65, animationDuration);


        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:

                        moveUpDown(Tabbed.toolbar,"DOWN");
                        //Tabbed.toolbar.setVisibility(View.VISIBLE);
                        mTextViewState.setText("Collapsed");




                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        mTextViewState.setText("Dragging...");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                       moveUpDown(Tabbed.toolbar,"UP");
                        mTextViewState.setText("Expanded");



                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        mTextViewState.setText("Hidden");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        mTextViewState.setText("Settling...");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //mTextViewState.setText("Sliding...");
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

    // In the SimpleOnGestureListener subclass you should override
    // onDown and any other gesture that you want to detect.
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
                moveUpDown(Tabbed.toolbar,"UP");
            }
            //Tabbed.toolbar.setVisibility(View.GONE);


            return true;
        }
    }
   public static void moveUpDown(final Toolbar view,String upDown){
       final View animatedView = Tabbed.toolbar;
       Integer finalHeight =Tabbed.toolbar.getMeasuredHeight();
       final AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) animatedView.getLayoutParams();
       ValueAnimator animator;
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
               animatedView.requestLayout();
           }
       });
       animator.setDuration(600);
       animator.start();
   }

}
