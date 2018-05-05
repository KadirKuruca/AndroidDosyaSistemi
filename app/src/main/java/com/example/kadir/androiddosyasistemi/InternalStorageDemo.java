package com.example.kadir.androiddosyasistemi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalStorageDemo extends AppCompatActivity {

    TextView tvDosyaAdlari,tvDosyaYolu;
    EditText etDosyaAdi,etMesaj,etSilinecekDosya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage_demo);
        init();


    }

    public void showFileList(View view) {

        String[] fileList = fileList();
        StringBuilder stringBuilder = new StringBuilder();
        for(String file : fileList){
            stringBuilder.append(file).append(", ");
        }
        tvDosyaAdlari.setText(stringBuilder);
    }

    public void showInternalStoragePath(View view) {

        String filePath = ""+getFilesDir();
        tvDosyaYolu.setText(filePath);
    }

    //Dosyaların Görüntüleneceği Activity e gitmek için
    public void showFiles(View view) {

        Intent intent = new Intent(InternalStorageDemo.this,InternalStorageGoruntule.class);
        startActivity(intent);
    }

    //Hafızaya bir dosya oluşturma ve içine yazma işlemi burada gerçekleşiyor.
    public void saveToInternalStorage(View view) {

        String fileName = etDosyaAdi.getText().toString();
        String data = etMesaj.getText().toString();

        FileOutputStream fos = null;

        try {

            fos = openFileOutput(fileName,MODE_PRIVATE);
            fos.write(data.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void deleteFile(View view) {

        String deleteFileName = etSilinecekDosya.getText().toString();
        boolean silindi = deleteFile(deleteFileName);
        if(silindi){
            Toast.makeText(this, "Dosya Silindi!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, ":Dosya Silinemedi!", Toast.LENGTH_SHORT).show();
    }

    private void init() {

        tvDosyaAdlari = findViewById(R.id.tvDosyaAdlari);
        tvDosyaYolu = findViewById(R.id.tvYol);
        etDosyaAdi = findViewById(R.id.etDosyaAdi);
        etMesaj = findViewById(R.id.etMesaj);
        etSilinecekDosya = findViewById(R.id.etSilinecekDosya);
    }
}
