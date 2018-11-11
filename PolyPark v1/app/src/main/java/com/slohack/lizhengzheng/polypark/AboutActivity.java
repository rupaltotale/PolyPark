package com.slohack.lizhengzheng.polypark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    Bundle bundle=getIntent().getExtras();

    ArrayList<PermitLot> data = (ArrayList)bundle.get("parkinglist");


}
