package com.example.rupalt.polyparkrupalapp;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

//    ImageView map = findViewById(R.id.map);
//    View sumbit = findViewById(R.id.button);
//    Spinner permit = findViewById(R.id.permit);
//    Spinner time = findViewById(R.id.time);
//    TextView when = findViewById(R.id.textView2);
//    TextView permitText = findViewById(R.id.textView5);
//    EditText startTime = findViewById(R.id.StartTime);
//    EditText endTime = findViewById(R.id.EndTime);
//    View filter = findViewById(R.id.filter);
    private double starttime;
    private double endtime;
    Spinner dropdown1;
    Spinner dropdown2;
    private String permittype;
    private String timeperiod;

    EditText StartTime;
    EditText EndTime;
    Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);

        View sumbit = findViewById(R.id.button);
        Spinner permit = findViewById(R.id.permit);
        Spinner time = findViewById(R.id.time);
        TextView when = findViewById(R.id.textView2);
        TextView permitText = findViewById(R.id.textView5);
        EditText startTime = findViewById(R.id.StartTime);
        EditText endTime = findViewById(R.id.EndTime);
        View filter = findViewById(R.id.filter);
        sumbit.setVisibility(View.INVISIBLE);
        permit.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
        when.setVisibility(View.INVISIBLE);
        permitText.setVisibility(View.INVISIBLE);
        startTime.setVisibility(View.INVISIBLE);
        endTime.setVisibility(View.INVISIBLE);

        //time drop down menu
        dropdown1 = findViewById(R.id.time);
        String[] items1 = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);
//        dropdown1.setOnItemSelectedListener(adapter1);

        //permit drop down menu
        dropdown2 = findViewById(R.id.permit);
        String[] items2 = new String[]{"N/A", "ABC", "XYZ", "EFG", "MNO", "PQR"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
//        dropdown2.setOnItemSelectedListener(this);

        StartTime = (EditText) findViewById(R.id.StartTime);
        EndTime = (EditText) findViewById(R.id.EndTime);
        submitButton = (Button) findViewById(R.id.button);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        ArrayList<PermitLot> lots = new ArrayList<>();
        try {

            Time start = new Time(13.5);
            Time end = new Time(15.5);
            String permit = "ABC";
            String day = "Tuesday";
            boolean hasPermit = false;
            lots = Fetcher.getParkingSpaces(this, permit, start, end, day, hasPermit, true);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (PermitLot pl: lots){
            LatLng sydney = new LatLng(pl.getLoc().getLatitude(), pl.getLoc().getLongitude());
            googleMap.addMarker(new MarkerOptions().position(sydney)
                    .title(pl.getLot())).setSnippet(pl.getInfo());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 20.0f));
        }
    }

    public void goToFilteredView(View view) {

        String starTtime = StartTime.getText().toString();
        String enEtime = EndTime.getText().toString();
        starttime = Double.parseDouble(starTtime.substring(0, 2))+
                Double.parseDouble(starTtime.substring(3, 5))/60;
        endtime =  Double.parseDouble(enEtime.substring(0, 2))/
                Double.parseDouble(enEtime.substring(3, 5))/60;

        String mypermit = dropdown2.getSelectedItem().toString();
        String mytime = dropdown1.getSelectedItem().toString();
        Time start = new Time(starttime);
        Time end = new Time(endtime);
        boolean hasPermit = true;

        if (mypermit == "N/A") { hasPermit = false;}

        try {
            ArrayList<PermitLot> lots = Fetcher.getParkingSpaces(this, mypermit, start, end, mytime, hasPermit, false);
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            intent.putExtra("myPermit", mypermit);
            intent.putExtra("start", start.getDecimalTime());
            intent.putExtra("end", end.getDecimalTime());
            intent.putExtra("myTime", mytime);
            intent.putExtra("hasPermit", hasPermit);
//            Toast.makeText(this, lots.toString(), Toast.LENGTH_LONG);
            startActivity(intent);
        }

        catch (Exception e) {}

    }
    public void filter(View view){

        View map = findViewById(R.id.googleMap);
        View sumbit = findViewById(R.id.button);
        Spinner permit = findViewById(R.id.permit);
        Spinner time = findViewById(R.id.time);
        TextView when = findViewById(R.id.textView2);
        TextView permitText = findViewById(R.id.textView5);
        EditText startTime = findViewById(R.id.StartTime);
        EditText endTime = findViewById(R.id.EndTime);
        View filter = findViewById(R.id.filter);
        filter.setVisibility(View.INVISIBLE);
        map.setVisibility(View.INVISIBLE);
        sumbit.setVisibility(View.VISIBLE);
        permit.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);
        when.setVisibility(View.VISIBLE);
        permitText.setVisibility(View.VISIBLE);
        startTime.setVisibility(View.VISIBLE);
        endTime.setVisibility(View.VISIBLE);
    }
}
