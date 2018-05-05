package com.example.kadir.androiddosyasistemi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InternalStorageGoruntule extends AppCompatActivity {

    EditText etShowFile;
    TextView tvFileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage_goruntule);
        init();
    }

    public void showFileContent(View view) {

        String fileName = etShowFile.getText().toString();
        StringBuffer buffer = new StringBuffer();
        FileInputStream fis = null;

        try {

            fis=openFileInput(fileName);
            int read;
            while((read = fis.read()) != -1)
            {
                Log.e("KADİR",""+read);
                Log.e("KADİR",""+(char)read);
                buffer.append((char) read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        tvFileContent.setText(buffer);
    }

    private void init() {
        etShowFile = findViewById(R.id.etShowFile);
        tvFileContent = findViewById(R.id.tvFileContent);
    }
}
