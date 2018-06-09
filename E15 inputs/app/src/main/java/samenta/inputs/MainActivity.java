package samenta.inputs;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // codes for disable Headset/Handsfree input jack
        AudioManager audio = (AudioManager)getApplicationContext().getSystemService(AUDIO_SERVICE);


        if(audio.isWiredHeadsetOn())
        {
            audio.setWiredHeadsetOn(false);
            audio.setSpeakerphoneOn(true);

        }




    }
}
