package com.example.main.activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.main.R;
import com.example.main.helper.DBHelper;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

        Button btnAdd,btnRead,btnClear,btnNext,btn;
        EditText edit1,edit2;
        DBHelper dbHelper;

        Cursor idIndex,nameIndex,emailIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnAdd =  findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        btnNext = findViewById(R.id.btnnext);
        btnNext.setOnClickListener(this);


        edit1 = findViewById(R.id.ed1);
        edit2 =  findViewById(R.id.ed2);


        dbHelper = new DBHelper(this);





        edit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() <= 0){
                    edit2.setError("Не может быть пустым");
                } else{
                    edit2.setError(null);
                }

            }

        });
        edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() <= 0){
                    edit1.setError("Не может быть пустым");
                } else{
                    edit1.setError(null);
                }
            }
        });

    }


    public void onClick(View v){
        if (edit1.getText().toString().isEmpty() && edit2.getText().toString().isEmpty()){
            edit1.setError("Поле не может быть пустым");
            edit2.setError("Поле не может быть пустым");
        } else {
            switch (v.getId()) {
                case R.id.btnnext:
                    Intent intent = new Intent(this, ThirdActivity.class);

                    startActivity(intent);

                    break;
                default:
                    break;

            }}

            String mail = edit2.getText().toString();
            String password = edit1.getText().toString();

            SQLiteDatabase database = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();

        switch (v.getId()) {

            case R.id.btnAdd:
                if (edit1.getText().toString().isEmpty() && edit2.getText().toString().isEmpty()){
                    edit1.setError("Поле должно быть заполнено");
                    edit2.setError("Поле должно быть заполнено");
                } else {


                    contentValues.put(DBHelper.KEY_NAME, mail);
                    contentValues.put(DBHelper.KEY_MAIL, password);

                    database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);

                }
                break;
            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);


                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", email  = " + cursor.getString(nameIndex) +
                                ", password = " + cursor.getString(emailIndex));
                    } while (cursor.moveToNext());
                } else {
                    Log.d("mlog", "0 rows");

                    cursor.close();
                    break;
                }
            case R.id.btnClear:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;

        }



        dbHelper.close();

        }
    }


