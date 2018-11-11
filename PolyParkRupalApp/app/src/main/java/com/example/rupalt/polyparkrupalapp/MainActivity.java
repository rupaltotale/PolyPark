package com.example.rupalt.polyparkrupalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    ImageView map = findViewById(R.id.map);
    View sumbit = findViewById(R.id.button);
    Spinner permit = findViewById(R.id.permit);
    Spinner time = findViewById(R.id.time);
    TextView when = findViewById(R.id.textView2);
    TextView permitText = findViewById(R.id.textView5);
    EditText startTime = findViewById(R.id.StartTime);
    EditText endTime = findViewById(R.id.EndTime);
    View filter = findViewById(R.id.filter);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        View sumbit = findViewById(R.id.button);
//        Spinner permit = findViewById(R.id.permit);
//        Spinner time = findViewById(R.id.time);
//        TextView when = findViewById(R.id.textView2);
//        TextView permitText = findViewById(R.id.textView5);
//        EditText startTime = findViewById(R.id.StartTime);
//        EditText endTime = findViewById(R.id.EndTime);
//        View filter = findViewById(R.id.filter);
//        sumbit.setVisibility(View.INVISIBLE);
//        permit.setVisibility(View.INVISIBLE);
//        time.setVisibility(View.INVISIBLE);
//        when.setVisibility(View.INVISIBLE);
//        permitText.setVisibility(View.INVISIBLE);
//        startTime.setVisibility(View.INVISIBLE);
//        endTime.setVisibility(View.INVISIBLE);
    }
    public void goToFilteredView(View view) {
        Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
        MainActivity.this.startActivity(myIntent);


    }
    public void filter(View view){
//        filter.setVisibility(View.INVISIBLE);
//        permit.setVisibility(View.VISIBLE);
//        time.setVisibility(View.VISIBLE);
//        when.setVisibility(View.VISIBLE);
//        permitText.setVisibility(View.VISIBLE);
//        startTime.setVisibility(View.VISIBLE);
//        endTime.setVisibility(View.VISIBLE);
    }
}
