package com.example.kadir.androiddosyasistemi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CacheStorageDemo extends AppCompatActivity {

    EditText etData1,etData2;
    TextView tvInternalCache,tvExternalCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_storage_demo);
        init();

    }

    private void init() {

        etData1 = findViewById(R.id.editText);
        etData2 = findViewById(R.id.editText2);
        tvInternalCache = findViewById(R.id.tvDataInternalCache);
        tvExternalCache = findViewById(R.id.tvDataExternalCache);
    }

    public void saveDataToInternalCache(View view) {

        String cacheData = etData1.getText().toString();

        File cacheFolder = getCacheDir();
        File cacheFile = new File(cacheFolder,"cache_file.txt");

        writeToFile(cacheFile,cacheData);
    }

    public void loadDataFromInternalCache(View view) {

        File cacheFolder = getCacheDir();
        File cacheFile = new File(cacheFolder,"cache_file.txt");

        tvInternalCache.setText(loadFromFile(cacheFile).toString());
    }

    public void saveDataToExternalCache(View view) {

        String cacheData = etData2.getText().toString();

        File cacheExternalFolder = getExternalCacheDir();
        File cacheFile = new File(cacheExternalFolder,"cache_external_file.txt");

        writeToFile(cacheFile,cacheData);
    }

    public void loadDataFromExternalCache(View view) {

        File cacheExternalFolder = getExternalCacheDir();
        File cacheExternalFile = new File(cacheExternalFolder,"cache_external_file.txt");

        tvExternalCache.setText(loadFromFile(cacheExternalFile).toString());

    }

    //Dosya Oluşturmak ve İçerisine yazmak için ortak bir metod
    public void writeToFile(File file, String data){
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
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
                    Toast.makeText(this, "Dosyaya Yazma İşlemi Başarılı!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Cache Dosyasından kayıtlı veriyi okumak için ortak kullanılacak metod
    public StringBuffer loadFromFile(File file){
        StringBuffer buffer = new StringBuffer();

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            int read;
            while((read=fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }
}
