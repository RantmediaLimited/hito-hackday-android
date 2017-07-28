package com.rantmedia.hotintheoffice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rantmedia.hotintheoffice.models.DatabaseDataObject;
import com.rantmedia.hotintheoffice.models.TemperatureObject;
import com.rantmedia.hotintheoffice.models.TimeTemperatureObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    TextView temperatureTV;
    private ArrayList<TimeTemperatureObject> timeTemperatureObjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatureTV = (TextView) findViewById(R.id.temperatureTV);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                timeTemperatureObjects.clear();


                DatabaseDataObject data = dataSnapshot.getValue(DatabaseDataObject.class);

                temperatureTV.setText(data.getCurrent_temperature() + "°C");

                Log.d("TAG", "Value in data object: " + data.getCurrent_temperature());
                Log.d("TAG", "Hashmap random val: " + data.getTemperature_history().get("1501235927").getTemperature());

                Set<String> keys = data.getTemperature_history().keySet();
                for (String key: keys) {

                    Log.d("TAG", "Key: " + key + ", Value: " + data.getTemperature_history().get(key).getTemperature());

                    timeTemperatureObjects.add(new TimeTemperatureObject(Long.valueOf(key), data.getTemperature_history().get(key).getTemperature()));

                }







            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

    }





}
