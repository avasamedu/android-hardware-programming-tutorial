package ir.samenta.accelerometersensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtAx , txtAy , txtAz ;
    SeekBar sbX , sbY , sbZ ;

    SensorManager mSensorManager ;
    Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAx = (TextView)findViewById(R.id.txtAx);
        txtAy = (TextView)findViewById(R.id.txtAy);
        txtAz = (TextView)findViewById(R.id.txtAz);

        sbX = (SeekBar)findViewById(R.id.seekBarX);
        sbY = (SeekBar)findViewById(R.id.seekBarY);
        sbZ = (SeekBar)findViewById(R.id.seekBarZ);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        SensorEventListener listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                {
                    float x = event.values[0];
                    float y = event.values[1];
                    float z = event.values[2];

                    txtAx.setText(String.valueOf(x));
                    txtAy.setText(String.valueOf(y));
                    txtAz.setText(String.valueOf(z));

                    sbX.setProgress((int)x);
                    sbY.setProgress((int)y);
                    sbZ.setProgress((int)z);


                }


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        mSensorManager.registerListener(listener,mSensor,SensorManager.SENSOR_DELAY_NORMAL);







    }
}
