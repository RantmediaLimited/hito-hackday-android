package com.rantmedia.hotintheoffice;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

       // myRef.setValue("Hello, World!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                TemperatureObject data = dataSnapshot.getValue(TemperatureObject.class);
                Log.d("TAG", "Value in data object: " + data.getCurrent_temperature());




                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {



                    Log.d("TAG", "snapshot: "+ dataSnapshot.toString());

                    Log.d("TAG", "value of temperature history: " + messageSnapshot.child("temperature_history"));

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });


//        myRef.setValue("Hello, work?");

    }
}
