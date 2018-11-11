package com.example.rupalt.polyparkrupalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Bundle bundle = getIntent().getExtras();
//intent.putExtra("myPermit", mypermit);
//            intent.putExtra("start", start.getDecimalTime());
//            intent.putExtra("end", end.getDecimalTime());
//            intent.putExtra("myTime", mytime);
//            intent.putExtra("hasPermit", hasPermit);
        String myPermit = bundle.getString("myPermit");
        Time start = new Time ((double)bundle.get("start"));
        Time end = new Time ((double)bundle.get("end"));
        String day = bundle.getString("myTime");
        boolean hasPermit = bundle.getBoolean("hasPermit");
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        ArrayList<PermitLot> lots = null;
        try {
            lots = Fetcher.getParkingSpaces(this, myPermit, start, end, day, hasPermit, false);
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

}
