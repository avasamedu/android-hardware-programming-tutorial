package ir.samenta.recorder;

import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    Button BtnStart , BtnStop ;
    MediaRecorder recorder ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnStart = (Button)findViewById(R.id.btnStart);
        BtnStop = (Button)findViewById(R.id.btnStop);

        BtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecording();
            }
        });

        BtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stopRecordign();
            }
        });


    }

    private void startRecording() {
        recorder = new MediaRecorder() ;
        //setting

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(AudioFormat.ENCODING_PCM_16BIT);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        recorder.setAudioChannels(1);
        recorder.setAudioEncodingBitRate(128000);
        recorder.setAudioSamplingRate(44100);



        recorder.setOutputFile(getVoicePathName());

        try {

            recorder.prepare();
            recorder.start();
            Log.e("Mic : ","Start recording. ..");

        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    private String getVoicePathName() {
        String fPath = Environment.getExternalStorageDirectory().getPath();
        File voice = new File(fPath,"_SimpleRecorder");
        if (! voice.exists())
        {
            voice.mkdir();
        }
        String FinalPath = voice.getAbsolutePath()+"/"+System.currentTimeMillis()+".mp3";
        Log.e("final file : ",FinalPath);

        return FinalPath;
    }

    private void stopRecordign() {

        if (recorder != null)
        {
            Log.e("Mic:","Not Null . Begin Stopping");
            recorder.stop();
            recorder.reset();
            recorder.release();
            recorder = null;
            Log.e("Mic : ", "Stoped !");
            Toast.makeText(this, "Recorded Voice Saved ! ", Toast.LENGTH_SHORT).show();
        }
    }
}
