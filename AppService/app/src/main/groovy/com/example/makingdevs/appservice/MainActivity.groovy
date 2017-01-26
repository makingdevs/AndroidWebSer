package com.example.makingdevs.appservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import groovy.transform.CompileStatic;

@CompileStatic
public class MainActivity extends AppCompatActivity {
    Button mButtonSend
    Button mButtonDelete





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonDelete = (Button) findViewById(R.id.button_delete)
        mButtonSend = (Button) findViewById(R.id.button_delete)
    }
}
