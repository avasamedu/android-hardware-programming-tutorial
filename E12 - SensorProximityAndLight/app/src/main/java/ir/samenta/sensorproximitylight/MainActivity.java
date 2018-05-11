package ir.samenta.sensorproximitylight;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManagerProximiy , sensorManagerLight ;
    Sensor sensorProximity , sensorLight ;

    TextView txtProximity , txtLight;
    LinearLayout lnrRoot ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLight = (TextView)findViewById(R.id.txtaLight);
        txtProximity = (TextView)findViewById(R.id.txtProximity);
        lnrRoot = (LinearLayout) findViewById(R.id.lnr_root);

        sensorManagerProximiy = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensorManagerLight = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        sensorProximity = sensorManagerProximiy.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorLight = sensorManagerLight.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManagerProximiy.registerListener(this,sensorProximity,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManagerLight.registerListener(this,sensorLight,SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManagerLight.unregisterListener(this);
        sensorManagerProximiy.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY)
            {
                // sensor is proximity
                if (sensorEvent.values[0]<4)
                {
                   lnrRoot.setBackgroundColor(ContextCompat.getColor(this,R.color.colorAccent));

                    ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_MUSIC,1000);
                    tg.startTone(ToneGenerator.TONE_CDMA_PIP,100);

                }else if (sensorEvent.values[0]>4)
                {
                    lnrRoot.setBackgroundColor(ContextCompat.getColor(this,R.color.colorProximity1));
                }

            }
            if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT)
            {
                // sensor is light
                txtLight.setText(String.valueOf(sensorEvent.values[0]));
            }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
