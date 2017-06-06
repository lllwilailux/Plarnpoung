package com.alien.xenocorez.plarnpoung;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class FoodCalculateActivity extends ListActivity {

    // List view
    private ListView lv;

    // Listview Adapter
    foodItemAdapter_xtended adapter;

    // Search EditText
    EditText inputSearch;


    // ArrayList for Listview
    ArrayList<foodItem> productList;

    MyDBHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //CheckedTextView check = (CheckedTextView) v;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_calculate);

        ArrayList<foodItem> products = new ArrayList<>();
        dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase();

        String[] queryColumns = new String[] { "_id", dbHelper.COL_NAME, dbHelper.COL_CAL};

        cursor = db.query(dbHelper.TABLE_NAME, queryColumns, null, null, null, null, null);

        /**********/
        /*ContentValues cv = new ContentValues();
        cv.put(dbHelper.COL_NAME, "Test2");
        cv.put(dbHelper.COL_CAL, 100);
        db.insert(dbHelper.TABLE_NAME,null,cv );*/

        /***********/

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


        lv = getListView();
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        inputSearch = (EditText) findViewById(R.id.inputSearch);

        // Adding items to listview
        adapter = new foodItemAdapter_xtended(this, android.R.layout.simple_list_item_checked, products);
        setListAdapter(adapter);
        //lv.setAdapter(adapter);

        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                FoodCalculateActivity.this.adapter.getFilter().filter(s);
            }
        };

        inputSearch.addTextChangedListener(tw);
        Button btnx = (Button)findViewById(R.id.button5);

        btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView lv = getListView();
                int childCount = lv.getChildCount();
                int total = 0;
                for (int i = 0; i < childCount; i++)
                {
                    View vx = lv.getChildAt(i);
                    CheckBox tx = (CheckBox) vx.findViewById(R.id.checkBox);
                    if(tx.isChecked()){
                        TextView tvx = (TextView) vx.findViewById(R.id.product_cal);
                        total += Integer.parseInt(tvx.getText().toString());
                    }
                }
                TextView tv444 = (TextView) findViewById(R.id.textView5);
                tv444.setText("Total Calories = " + Integer.toString(total));

            }
        });

    }


}
