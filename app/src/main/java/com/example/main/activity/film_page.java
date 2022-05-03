package com.example.main.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.main.R;
import com.example.main.helper.DBHelper;

import java.util.UUID;

public class film_page extends AppCompatActivity {
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_page);
        ConstraintLayout filmBG = findViewById(R.id.filmPageBg);
        ImageView filmImage = findViewById(R.id.filmPageImage);
        TextView filmTitle = findViewById(R.id.filmPageTitle);
        TextView filmDesc = findViewById(R.id.filmPageDesc);
        TextView filmDate = findViewById(R.id.filmPageDate);
        TextView filmType = findViewById(R.id.filmPageType);
        TextView cur_place = findViewById(R.id.tPagePlace);

        filmDesc.setMovementMethod(new ScrollingMovementMethod());

        filmBG.setBackgroundColor(getIntent().getIntExtra("filmBG",0));
        filmImage.setImageResource(getIntent().getIntExtra("img",0));
        filmTitle.setText(getIntent().getStringExtra("TitleName"));
        filmDesc.setText(getIntent().getStringExtra("text"));
        filmDate.setText(getIntent().getStringExtra("date"));
        filmType.setText(getIntent().getStringExtra("type"));
        cur_place.setText(getIntent().getStringExtra("place"));
        dbHelper = new DBHelper(this);
        Button btn = findViewById(R.id.button2);
        Button btn1 = findViewById(R.id.read);
        Button btn2 = findViewById(R.id.clear);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        class randomStringGenerator {
            public void main(String[] args) {
                System.out.println(generateString());
            }

            public String generateString() {
                String uuid = UUID.randomUUID().toString();
                return uuid;
            }
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = getIntent().getStringExtra("TitleName");
                String date = getIntent().getStringExtra("date");
                String place = getIntent().getStringExtra("place");
                String personal_id = new randomStringGenerator().generateString();


                ContentValues contentValues1 = new ContentValues();
                switch (view.getId()){
                    case R.id.button2:
                        Toast toast = Toast.makeText(getApplicationContext(),"Вы зарегестрировались на " + title + " на дату " + date + " место " + place, Toast.LENGTH_LONG);
                        toast.show();
                        contentValues1.put(DBHelper.KEY_TITLE, title);
                        contentValues1.put(DBHelper.KEY_DATE, date);
                        contentValues1.put(DBHelper.KEY_PLACE, place);
                        contentValues1.put(DBHelper.KEY_PERSONAL_ID, String.valueOf(personal_id));


                        database.insert(DBHelper.TABLE_TITLE, null, contentValues1);

                        break;

                }

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.read:
                        Cursor cursor = database.query(DBHelper.TABLE_TITLE, null, null, null, null, null, null);
                        if (cursor.moveToFirst()) {
                            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                            int TitleIndex = cursor.getColumnIndex(DBHelper.KEY_TITLE);
                            int DateIndex = cursor.getColumnIndex(DBHelper.KEY_DATE);
                            int PlaceIndex = cursor.getColumnIndex(DBHelper.KEY_PLACE);
                            int Pers_Id = cursor.getColumnIndex(DBHelper.KEY_PERSONAL_ID);

                            do {
                                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                        ", Название фильма: " + cursor.getString(TitleIndex) +
                                        ", Дата: " + cursor.getString(DateIndex) +
                                        ", Место: " + cursor.getString(PlaceIndex) +
                                        ", Ваш персональный номер брони: " + cursor.getString(Pers_Id));
                            } while (cursor.moveToNext());
                        } else {
                            Log.d("mlog", "0 rows");

                            cursor.close();
                            break;
                        }

                }
            }



        });
    btn2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.clear:
                    database.delete(DBHelper.TABLE_TITLE, null, null);
                    break;
            }
            dbHelper.close();
        }
    });




    }

}