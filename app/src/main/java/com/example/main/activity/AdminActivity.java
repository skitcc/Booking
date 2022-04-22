package com.example.main.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main.R;
import com.example.main.helper.DBHelper;

public class AdminActivity extends AppCompatActivity {
    DBHelper dbHelper;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_actiity);
        LinearLayout bg = findViewById(R.id.mainList);
        ImageView filmImage = findViewById(R.id.image);
        TextView filmName = findViewById(R.id.textName);
        TextView filmPassword = findViewById(R.id.textPassword);
        TextView filmTitle = findViewById(R.id.textTitle);
        TextView filmDate = findViewById(R.id.textDate);

        bg.setBackgroundColor(getIntent().getIntExtra("filmBG",0));
        filmImage.setImageResource(getIntent().getIntExtra("img",0));
        filmTitle.setText(getIntent().getStringExtra("TitleName"));
        filmDate.setText(getIntent().getStringExtra("date"));
        filmName.setText(getIntent().getStringExtra("email"));
        filmPassword.setText(getIntent().getStringExtra("password"));




    }
}