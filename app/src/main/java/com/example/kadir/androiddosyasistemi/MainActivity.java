package com.example.kadir.androiddosyasistemi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openInternalStorageDemo(View view) {

        Intent intent = new Intent(this,InternalStorageDemo.class);
        startActivity(intent);
    }

    public void openCacheStorageDemo(View view) {

        Intent intent = new Intent(this,CacheStorageDemo.class);
        startActivity(intent);
    }
}
