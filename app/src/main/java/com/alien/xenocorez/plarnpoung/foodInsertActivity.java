package com.alien.xenocorez.plarnpoung;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class foodInsertActivity extends ActionBarActivity {

    MyDBHelper dbHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_insert);
        dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase();

        final Button FoodListBtn = (Button) findViewById(R.id.button4);
        final EditText TB3 = (EditText) findViewById(R.id.editText);
        final EditText TB4 = (EditText) findViewById(R.id.editText2);
        FoodListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                String s1 = TB3.getText().toString();
                Integer s2 = Integer.parseInt(TB4.getText().toString());
                cv.put(dbHelper.COL_NAME, s1);
                cv.put(dbHelper.COL_CAL, s2);

                db.insert(dbHelper.TABLE_NAME, null, cv);
                foodListAlert();
            }
        });
    }
    protected void foodListAlert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Food added");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        builder.show();
    }


}