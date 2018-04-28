package ir.samenta.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    Button btnCheck , btnWifiSwitch , btnDataSwitch ;
    TextView txtStatus ;

    Integer DataType = 0; // 0=off , 1=wifi , 2=data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCheck = (Button)findViewById(R.id.btnCheckNet) ;
        btnWifiSwitch = (Button)findViewById(R.id.btnWifiSwitch) ;
        btnDataSwitch = (Button)findViewById(R.id.btnDataSwitch) ;
        txtStatus = (TextView) findViewById(R.id.txtStatus) ;

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStatusMethod();
            }
        });

        btnWifiSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStatusMethod();
                if (DataType == 1)
                {
                    // turn off wifi
                    turnWifi(false);
                }else
                {
                    // turn on wifi
                    turnWifi(true);
                }
            }
        });

        btnDataSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStatusMethod();
                if (DataType == 2)
                {
                    //turn off data
                    turnData(getApplicationContext(),false);
                }else
                {
                    //turn on data
                    turnData(getApplicationContext(),true);

                }
            }
        });




    }

    private void turnData(Context context , boolean enable) {

        final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
         Class conmanClass = null;
        try {
            conmanClass = Class.forName(conman.getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {

        final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
        iConnectivityManagerField.setAccessible(true);
        final Object iConnectivityManager;

            iConnectivityManager = iConnectivityManagerField.get(conman);
            final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
            final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            setMobileDataEnabledMethod.setAccessible(true);
            setMobileDataEnabledMethod.invoke(iConnectivityManager, enable);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void turnWifi(boolean enable) {
        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (enable)
        {
            wifiManager.setWifiEnabled(true);
        }else
        {
            wifiManager.setWifiEnabled(false);
        }

    }

    private void checkStatusMethod() {


        ConnectivityManager conMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo mobileData = conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isConnectedOrConnecting())
        {
            txtStatus.setText("وای فای وصل است");
            DataType = 1;
        } else if (mobileData.isConnectedOrConnecting())
        {
            txtStatus.setText("دیتای موبایل وصل است");
            DataType = 2 ;
        }else {
            txtStatus.setText("اینترنت شما قطع میباشد");
            DataType = 0;
        }







    }
}
