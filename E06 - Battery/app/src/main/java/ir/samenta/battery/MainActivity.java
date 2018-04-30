package ir.samenta.battery;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtBtrPercent ;
    ProgressBar progressBarBtr;
    Button BtnCheck ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBtrPercent = (TextView)findViewById(R.id.txtBtrPercent);
        progressBarBtr = (ProgressBar)findViewById(R.id.progressBarBtr);
        BtnCheck = (Button)findViewById(R.id.btnCheckBtr);

        BtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               float percent = showBatteryStatus();
               txtBtrPercent.setText(String.valueOf(percent));
               progressBarBtr.setProgress((int)percent);

                checkCharginStatus();
            }
        });

    }

    private float showBatteryStatus() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent btrStatus = getApplicationContext().registerReceiver(null,filter);

        int level = btrStatus != null ? btrStatus.getIntExtra(BatteryManager.EXTRA_LEVEL,-1):-1;
        int scale = btrStatus != null ? btrStatus.getIntExtra(BatteryManager.EXTRA_SCALE,-1):-1;

        float btrPercent =  (float) level /scale;
        return (btrPercent*100);

    }



    private void checkCharginStatus() {

        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent btrStatus = getApplicationContext().registerReceiver(null,filter);

        int chargeStatus = btrStatus.getIntExtra(BatteryManager.EXTRA_STATUS,-1);


        switch (chargeStatus)
        {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                Toast.makeText(this, "باطری داره شارژ میشه", Toast.LENGTH_SHORT).show();
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                Toast.makeText(this, "باطری پره", Toast.LENGTH_SHORT).show();
                break;

                default:
                    Toast.makeText(this, "شارژ انجام نمیگیره", Toast.LENGTH_SHORT).show();
        }
    }


}
