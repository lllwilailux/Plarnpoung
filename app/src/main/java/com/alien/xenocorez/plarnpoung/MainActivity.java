package com.alien.xenocorez.plarnpoung;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button FoodListBtn = (Button) findViewById(R.id.button);
        FoodListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this,FoodListActivity.class);
                startActivity(t);
            }
        });

        final Button AddBTN = (Button) findViewById(R.id.button2);
        AddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this,foodInsertActivity.class);
                startActivity(t);
            }
        });

        final Button CalcBtn = (Button) findViewById(R.id.button3);
        CalcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this,FoodCalculateActivity.class);
                startActivity(t);
            }
        });
    }

}
