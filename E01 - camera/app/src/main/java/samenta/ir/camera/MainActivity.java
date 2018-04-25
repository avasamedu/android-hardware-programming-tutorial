package samenta.ir.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int CAMERA_PICTURE_REQEST = 1333 ;
    Button BtnTakePic ;
    ImageView ImgCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define view
        BtnTakePic = (Button)findViewById(R.id.btnTakePic);
        ImgCamera  = (ImageView)findViewById(R.id.imageViewCamera);


        BtnTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open camera and take picture

                Intent photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photo,CAMERA_PICTURE_REQEST);



            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_PICTURE_REQEST)
        {
           if (resultCode == RESULT_OK)
           {
               Bitmap imageData = (Bitmap)data.getExtras().get("data");
               ImgCamera.setImageBitmap(imageData);


           }else if(resultCode == RESULT_CANCELED)
           {
               Toast.makeText(this, "user canceled picture taken", Toast.LENGTH_SHORT).show();
           }


        }else
        {
            Log.e("result msg:","this request is not mine");

        }

    }
}
