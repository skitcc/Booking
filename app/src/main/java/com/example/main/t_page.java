package com.example.main;

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

import com.example.main.helper.DBHelper;

public class t_page extends AppCompatActivity {
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpage);
        ConstraintLayout filmBG = findViewById(R.id.tPageBg);
        ImageView filmImage = findViewById(R.id.tPageImage);
        TextView filmTitle = findViewById(R.id.tPageTitle);
        TextView filmDesc = findViewById(R.id.tPageDesc);
        TextView filmDate = findViewById(R.id.tPageDate);
        TextView filmType = findViewById(R.id.tPageType);
        TextView place = findViewById(R.id.tPagePlace);

        filmDesc.setMovementMethod(new ScrollingMovementMethod());

        filmBG.setBackgroundColor(getIntent().getIntExtra("filmBG",0));
        filmImage.setImageResource(getIntent().getIntExtra("img",0));
        filmTitle.setText(getIntent().getStringExtra("TitleName"));
        filmDesc.setText(getIntent().getStringExtra("text"));
        filmDate.setText(getIntent().getStringExtra("date"));
        filmType.setText(getIntent().getStringExtra("type"));
        place.setText(getIntent().getStringExtra("place"));
        dbHelper = new DBHelper(this);
        Button btn = findViewById(R.id.tbutton2);
        Button btn1 = findViewById(R.id.tread);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title_balet = getIntent().getStringExtra("TitleName");
                String date_balet = getIntent().getStringExtra("date");
                String place = getIntent().getStringExtra("place");
                Toast toast = Toast.makeText(getApplicationContext(),"Вы зарегестрировались на " + title_balet + " на дату " + date_balet + " место " + place, Toast.LENGTH_LONG);
                toast.show();
                ContentValues contentValues1 = new ContentValues();
                switch (view.getId()){
                    case R.id.tbutton2:
                        contentValues1.put(DBHelper.KEY_TITLE_BALET, title_balet);
                        contentValues1.put(DBHelper.KEY_DATE_BALET, date_balet);
                        contentValues1.put(DBHelper.KEY_PLACE, place);



                        database.insert(DBHelper.TABLE_BALETS, null, contentValues1);


                }

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()){
                    case R.id.tread:
                        Cursor cursor = database.query(DBHelper.TABLE_BALETS, null, null, null, null, null, null);
                        if (cursor.moveToFirst()) {
                            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                            int TitleBaletIndex = cursor.getColumnIndex(DBHelper.KEY_TITLE);
                            int DateBaletIndex = cursor.getColumnIndex(DBHelper.KEY_DATE);
                            int PlaceIndex = cursor.getColumnIndex(DBHelper.KEY_PLACE);


                            do {
                                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                        ", title = " + cursor.getString(TitleBaletIndex) +
                                        ", date = " + cursor.getString(DateBaletIndex) +
                                        ", place = " + cursor.getString(PlaceIndex));
                            } while (cursor.moveToNext());
                        } else {
                            Log.d("mlog", "0 rows");

                            cursor.close();
                            break;
                        }
                    case R.id.tclear:
                        database.delete(DBHelper.TABLE_BALETS, null, null);
                        break;
                }dbHelper.close();
            }


        });
    }
}