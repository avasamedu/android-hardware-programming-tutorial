package ir.samenta.vibration;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Vibrator mVibrator ;
    long[] vibrationPattern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrationPattern = new long[]{0, 1000, 100};
        if (mVibrator.hasVibrator())
        {
//            // vibration exist on device
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                mVibrator.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
//            }else

//            {
                mVibrator.vibrate(vibrationPattern,0);
                //mVibrator.vibrate(1000);
//            }

        }else
        {
            // vibration Not exist in this device
        }



    }
}
