package ir.samenta.speaker;

import android.content.Context;
import android.media.AudioManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    Button btnUp , btnDown ;
    ImageButton imgBtnMute , imgBtnUnmute;
    AudioManager am ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        am = (AudioManager)MainActivity.this.getSystemService(Context.AUDIO_SERVICE);


        btnDown = (Button)findViewById(R.id.btnVolumeDown);
        btnUp = (Button)findViewById(R.id.btnVolumUp);
        imgBtnMute = (ImageButton) findViewById(R.id.imgBtnMute);
        imgBtnUnmute = (ImageButton) findViewById(R.id.imgBtnUnmute);



    }


    public void Mute(View v)
    {
        am.setRingerMode(AudioManager.RINGER_MODE_SILENT);

    }
    public void UnMute(View v)
    {
        am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

        // set volume maximum

        int maxVolum = am.getStreamMaxVolume(AudioManager.STREAM_RING);
        am.setStreamVolume(AudioManager.STREAM_RING,maxVolum,AudioManager.FLAG_SHOW_UI+AudioManager.FLAG_PLAY_SOUND);



    }

    public void Up(View v)
    {
        am.adjustVolume(AudioManager.ADJUST_RAISE,AudioManager.FLAG_SHOW_UI+AudioManager.FLAG_PLAY_SOUND);

    }
    public void Down(View v)
    {
        am.adjustVolume(AudioManager.ADJUST_LOWER,AudioManager.FLAG_SHOW_UI+AudioManager.FLAG_PLAY_SOUND);
    }



}
