package ir.samenta.flashlight;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Camera mCamera;
    Boolean cameraExist ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
        {
            cameraExist = true;
        }
        else
        {
            Toast.makeText(this, "دستگاه شما فلش ساپورت نمیکند", Toast.LENGTH_SHORT).show();
            cameraExist = false;
        }


    }



    public void TurnOn(View v)
    {
        if (cameraExist) {
            mCamera = Camera.open();
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(parameters);
            mCamera.startPreview();
        }else
        {
            Toast.makeText(this, "فلش وجود ندارد", Toast.LENGTH_SHORT).show();
        }
    }

    public void TurnOff(View v)
    {
        if (cameraExist) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }else
        {
            Toast.makeText(this, "فلش وجود ندارد", Toast.LENGTH_SHORT).show();
        }

    }

}
