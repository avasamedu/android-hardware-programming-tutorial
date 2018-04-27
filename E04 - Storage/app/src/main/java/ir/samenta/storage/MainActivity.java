package ir.samenta.storage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    String Path ;
    String dir ;
    File file ;

    ListView listView ;
    ArrayAdapter adapter;

    File[] filelist ;
    File txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listview);



        Path = Environment.getExternalStorageDirectory().getAbsolutePath();


        dir = Path+"/SamentaStorage";
        file = new File(dir);

        if (!file.exists())
        {
            file.mkdir();
            filelist = file.listFiles();
        }else {
            filelist = file.listFiles();
        }
        String[] fileNames = new String[filelist.length];

        for (int i = 0 ; i < fileNames.length ; i++ )
       {
            fileNames[i] = filelist[i].getName();
       }

        //listview
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,fileNames);
        listView.setAdapter(adapter);

        Toast.makeText(this, "list view is completed ", Toast.LENGTH_SHORT).show();
        // ----------


        // create txt file and wrtie string into
       txt = new File(file, "apptest.txt");

       // writeSimple();

       // writeAppend();


        //DeleteDirs();


    }

    private void DeleteDirs() {

        if (file.isDirectory())
        {
            String[] childers = file.list();
            for (int i = 0 ; i <childers.length ; i++ )
            {
                new File(file,childers[i]).delete();

            }
        }
    }


    private void writeSimple() {

        try {
            FileWriter writer = new FileWriter(txt);
            writer.append("this txt writed from app ");
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void writeAppend() {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(
              new FileOutputStream(txt,true) , "UTF-8"
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedWriter bfwr = new BufferedWriter(writer);
        try {
            bfwr.write("\n appended txt here ...");
            bfwr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }


}
