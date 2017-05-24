package com.example.dell.shop2;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button p1,p2,p3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p1=(Button)findViewById(R.id.b1);
        p2=(Button)findViewById(R.id.b2);

    }
    public void store(View view)
    {
        Intent i=new Intent(getApplicationContext(),store.class);
        startActivity(i);
    }

    public void back(View view)
    {
        System.exit(0);
    }
}
