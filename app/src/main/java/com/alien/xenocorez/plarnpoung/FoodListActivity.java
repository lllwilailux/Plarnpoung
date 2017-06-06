package com.alien.xenocorez.plarnpoung;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class FoodListActivity extends ActionBarActivity {

    // List view
    private ListView lv;

    // Listview Adapter
    foodItemAdapter adapter;

    // Search EditText
    EditText inputSearch;


    // ArrayList for Listview
    ArrayList<foodItem> productList;

    MyDBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        ArrayList<foodItem> products = new ArrayList<>();
        dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase();

        String[] queryColumns = new String[] { "_id", dbHelper.COL_NAME, dbHelper.COL_CAL};

        cursor = db.query(dbHelper.TABLE_NAME, queryColumns, null, null, null, null, null);


        String[] showColumns = new String[] {dbHelper.COL_NAME, dbHelper.COL_CAL};

        if (cursor != null && cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                do {
                    foodItem contactListItems = new foodItem();

                    contactListItems.setFoodName(cursor.getString(cursor
                            .getColumnIndex("FoodName")));
                    contactListItems.setFoodCal((cursor.getInt(cursor
                            .getColumnIndex("FoodCal"))));
                    products.add(contactListItems);

                } while (cursor.moveToNext());
            }
        }
        cursor.close();

        productList = products;


        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        // Adding items to listview
        adapter = new foodItemAdapter(this, R.layout.list_item, products);
        lv.setAdapter(adapter);

        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                FoodListActivity.this.adapter.getFilter().filter(s);
            }
        };

        inputSearch.addTextChangedListener(tw);
    }

}
