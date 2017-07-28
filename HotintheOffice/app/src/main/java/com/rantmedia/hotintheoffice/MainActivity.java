package com.rantmedia.hotintheoffice;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rantmedia.hotintheoffice.adapters.HistoricalTemperatureAdapter;
import com.rantmedia.hotintheoffice.models.DatabaseDataObject;
import com.rantmedia.hotintheoffice.models.TemperatureObject;
import com.rantmedia.hotintheoffice.models.TimeTemperatureObject;
import com.rantmedia.hotintheoffice.utilities.GradientSetter;
import com.rantmedia.hotintheoffice.utilities.StatusBarHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    TextView temperatureTV, commentTV;
    private ArrayList<TimeTemperatureObject> timeTemperatureObjects = new ArrayList<>();
    FrameLayout baseLayout;
    double currentTemperature = 0.0;
    RecyclerView tempRV;
    LinearLayoutManager layoutManager;
    HistoricalTemperatureAdapter historicalTemperatureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseLayout = (FrameLayout) findViewById(R.id.baseLayout);
        temperatureTV = (TextView) findViewById(R.id.temperatureTV);
        commentTV = (TextView) findViewById(R.id.commentTV);
        RecyclerView tempRV = (RecyclerView) findViewById(R.id.tempRV);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        tempRV.setLayoutManager(layoutManager);

        historicalTemperatureAdapter = new HistoricalTemperatureAdapter(getParent(), getApplicationContext(), timeTemperatureObjects);
        tempRV.setAdapter(historicalTemperatureAdapter);


        setHotColours();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DatabaseDataObject data = dataSnapshot.getValue(DatabaseDataObject.class);

                timeTemperatureObjects.clear();
                if (data != null) {
                    temperatureTV.setText(data.getCurrent_temperature() + "°");
                    currentTemperature = data.getCurrent_temperature();

                    if (data.getTemperature_history() != null) {
                        Set<String> keys = data.getTemperature_history().keySet();
                        for (String key : keys) {
                            timeTemperatureObjects.add(new TimeTemperatureObject(Long.valueOf(key), data.getTemperature_history().get(key).getTemperature()));
                        }

                        handleCurrentTemperature();
                        historicalTemperatureAdapter.swapData(timeTemperatureObjects);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

    }


    private void handleCurrentTemperature() {


        if (currentTemperature < 0.5) {

            setFreezingColours();

        } else if (currentTemperature < 10.5) {

            setcoldColours();

        } else if (currentTemperature < 15.5) {

            setAverageColours();

        } else if (currentTemperature < 20.5) {

            setWarmColours();

        } else if (currentTemperature < 25.5) {

            setVeryWarmColours();

        } else if (currentTemperature < 30.5) {

            setHotColours();

        } else {

            setBoilingColours();
        }


    }

    private void setComment(String comment) {
        commentTV.setText(comment);
    }

    private void setBoilingColours() {
        StatusBarHelper.setStatusBarColour(getWindow(), getApplicationContext(), R.color.veryhot);
        GradientSetter.setGradientThreeColours(baseLayout, getApplicationContext(), R.color.veryhot, R.color.hot);
        setComment("It's roasting in here!");
    }

    private void setHotColours() {
        StatusBarHelper.setStatusBarColour(getWindow(), getApplicationContext(), R.color.veryhot);
        GradientSetter.setGradientTwoColours(baseLayout, getApplicationContext(), R.color.veryhot, R.color.hot);
        setComment("Suns out guns out!\n(unless its cloudy)");
    }

    private void setVeryWarmColours() {
        StatusBarHelper.setStatusBarColour(getWindow(), getApplicationContext(), R.color.hot);
        GradientSetter.setGradientThreeColours(baseLayout, getApplicationContext(), R.color.hot, R.color.warm);
        setComment("Wouldn't you rather be outside today?");
    }

    private void setWarmColours() {
        StatusBarHelper.setStatusBarColour(getWindow(), getApplicationContext(), R.color.warm);
        GradientSetter.setGradientTwoColours(baseLayout, getApplicationContext(), R.color.warm, R.color.average);
        setComment("No jacket needed");
    }


    private void setAverageColours() {
        StatusBarHelper.setStatusBarColour(getWindow(), getApplicationContext(), R.color.average);
        GradientSetter.setGradientThreeColours(baseLayout, getApplicationContext(), R.color.average, R.color.cool);
        setComment("Pretty average. Just like this comment.");
    }


    private void setcoldColours() {
        StatusBarHelper.setStatusBarColour(getWindow(), getApplicationContext(), R.color.cold);
        GradientSetter.setGradientThreeColours(baseLayout, getApplicationContext(), R.color.cold, R.color.verycold);
        setComment("Brrr!");
    }

    private void setFreezingColours() {
        StatusBarHelper.setStatusBarColour(getWindow(), getApplicationContext(), R.color.verycold);
        GradientSetter.setGradientThreeColours(baseLayout, getApplicationContext(), R.color.verycold, R.color.freezing);
        setComment("It's time to take a bath.\nInside of a volcano.");
    }

}
