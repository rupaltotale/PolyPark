package com.slohack.lizhengzheng.polypark;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;
import java.util.List;
import java.util.ArrayList;

public class Main_Page extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private double starttime;
    private double endtime;
    private String permittype;
    private String timeperiod;

    EditText StartTime;
    EditText EndTime;
    Button submitButton;

//    private final Spinner dropdown2;
//    private final Spinner dropdown1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__page);

        //time drop down menu
        final Spinner dropdown1 = findViewById(R.id.time);
        String[] items1 = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);
        dropdown1.setOnItemSelectedListener(this);

        //permit drop down menu
        final Spinner dropdown2 = findViewById(R.id.permit);
        String[] items2 = new String[]{"N/A", "ABC", "XYZ", "EFG", "MNO", "PQR"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
        dropdown2.setOnItemSelectedListener(this);

        StartTime = (EditText) findViewById(R.id.StartTime);
        EndTime = (EditText) findViewById(R.id.EndTime);
        submitButton = (Button) findViewById(R.id.button);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.print("did it run or no?");
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
                    List<PermitLot> mylots = Fetcher.getParkingSpaces(mypermit, start, end, mytime, hasPermit);
                    Intent intent = new Intent(Main_Page.this, AboutActivity.class);
                    ArrayList <PermitLot> mylot = (ArrayList)mylots;
                    intent.putExtra("parkinglist", mylot);
                    startActivity(intent);
                }

                catch (Exception e) {}

            }
        });

    }



    private void showToast(String text) {
        Toast.makeText(Main_Page.this, text, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {
        String text = parent.getItemAtPosition(position).toString();
        showToast(text);
    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        String text = "N/A";
        showToast(text);
    }

}

