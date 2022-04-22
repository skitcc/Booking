package com.example.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.main.R;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnSp, btnCin, btnThe, btnOth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);



        btnCin = findViewById(R.id.cinema);
        btnCin.setOnClickListener(this);

        btnThe = findViewById(R.id.theatres);
        btnThe.setOnClickListener(this);

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.cinema:
                Intent intent = new Intent(ThirdActivity.this, films.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        switch (v.getId()){
            case R.id.theatres:
                Intent intent = new Intent(this, TheatreActivity.class);
                startActivity(intent);
                break;
           default:
                break;
        }

    }


}