package com.example.foodar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton search_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_button=(FloatingActionButton)findViewById(R.id.search_rest_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t=Toast.makeText(getApplicationContext(),"Succesfull click",Toast.LENGTH_SHORT);
                t.show();
                Intent i=new Intent(MainActivity.this,Restraunt_selector.class);
                startActivity(i);
            }
        });
    }
}