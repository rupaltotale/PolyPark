package com.example.rupalt.polyparkrupalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    Spinner dropdown1; // Day
    Spinner dropdown2; // Permit
    EditText startTime;
    EditText endTime;
    Button submitButton;
    TextView whenText;
    TextView permitText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);

        submitButton = findViewById(R.id.button);
        dropdown2 = findViewById(R.id.permit);
        dropdown1 = findViewById(R.id.time);
        whenText = findViewById(R.id.textView2);
        permitText = findViewById(R.id.textView5);
        startTime = findViewById(R.id.StartTime);
        endTime = findViewById(R.id.EndTime);

        submitButton.setVisibility(View.INVISIBLE);
        dropdown2.setVisibility(View.INVISIBLE);
        dropdown1.setVisibility(View.INVISIBLE);
        whenText.setVisibility(View.INVISIBLE);
        permitText.setVisibility(View.INVISIBLE);
        startTime.setVisibility(View.INVISIBLE);
        endTime.setVisibility(View.INVISIBLE);

        //time drop down menu
        String[] items1 = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);
//        dropdown1.setLabelFor();
        //permit drop down menu
        String[] items2 = new String[]{"N/A", "ABC", "XYZ", "EFG", "MNO", "PQR"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        ArrayList<PermitLot> lots = new ArrayList<>();
        try {

            Time start = new Time(13.5);
            Time end = new Time(15.5);
            String permit = "ABC";
            String day = "Tuesday";
            lots = Fetcher.getParkingSpaces(this, permit, start, end, day, false, true);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (PermitLot pl: lots){
            LatLng sydney = new LatLng(pl.getLoc().getLatitude(), pl.getLoc().getLongitude());
            googleMap.addMarker(new MarkerOptions().position(sydney)
                    .title(pl.getLot())).setSnippet(pl.getInfo());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15.0f));
        }
    }

    public void goToFilteredView(View view) {

        double sTime = Double.parseDouble(startTime.getText().toString().replace(":", "."));
        double eTime = Double.parseDouble(endTime.getText().toString().replace(":", "."));
        double starttime = Math.floor(sTime) + (sTime - Math.floor(sTime))/0.6;
        double endtime =  Math.floor(eTime) + (eTime - Math.floor(eTime))/0.6;

        String mypermit = dropdown2.getSelectedItem().toString();
        String mytime = dropdown1.getSelectedItem().toString();
        Time start = new Time(starttime);
        Time end = new Time(endtime);
        boolean hasPermit = true;

        if (mypermit.equals( "N/A")) { hasPermit = false;}

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("myPermit", mypermit);
        intent.putExtra("start", start.getDecimalTime());
        intent.putExtra("end", end.getDecimalTime());
        intent.putExtra("myTime", mytime);
        intent.putExtra("hasPermit", hasPermit);
        startActivity(intent);


    }
    public void filter(View view){

        View map = findViewById(R.id.googleMap);
        View filter = findViewById(R.id.filter);
        
        filter.setVisibility(View.INVISIBLE);
        map.setVisibility(View.INVISIBLE);

        submitButton.setVisibility(View.VISIBLE);
        dropdown1.setVisibility(View.VISIBLE);
        dropdown2.setVisibility(View.VISIBLE);
        whenText.setVisibility(View.VISIBLE);
        permitText.setVisibility(View.VISIBLE);
        startTime.setVisibility(View.VISIBLE);
        endTime.setVisibility(View.VISIBLE);
    }
}
