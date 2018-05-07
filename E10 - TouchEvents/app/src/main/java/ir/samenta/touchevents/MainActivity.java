package ir.samenta.touchevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout lnrRoot , lnrPink;
    Display mdisplay ;
    int mHeight , mWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdisplay = getWindowManager().getDefaultDisplay();
        mHeight = mdisplay.getHeight();
        mWidth = mdisplay.getWidth();


        lnrRoot = (LinearLayout)findViewById(R.id.lnrRoot);


        lnrRoot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
               if (motionEvent.getX() <  mWidth/2)
               {
                   lnrRoot.setBackgroundColor(getResources().getColor(R.color.colorLeft));
               }else if(motionEvent.getX() >  mWidth/2)
               {
                   lnrRoot.setBackgroundColor(getResources().getColor(R.color.colorRight));
               }
                return false;
            }
        });



    }
}
