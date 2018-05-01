package ir.samenta.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int BluetoothOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter.enable())
        {
            // Bluetooth is ON
            BluetoothOn = 1;

            bluetoothAdapter.disable(); // Turn bluetooth OFF


        }else if(bluetoothAdapter.disable()){
            //Bluetooth is OFF
            BluetoothOn = 0 ;

            bluetoothAdapter.enable();//Turn bluetooth ON



        }

    }
}
