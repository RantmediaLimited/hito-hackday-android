package com.rantmedia.hotintheoffice.models;

import java.util.HashMap;

/**
 * Created by ginobasiletti on 28/07/2017.
 */

public class DatabaseDataObject {
    private double current_temperature;
    private HashMap<String, TemperatureObject> temperature_history;


    public double getCurrent_temperature() {
        return current_temperature;
    }

    public HashMap<String, TemperatureObject> getTemperature_history() {
        return temperature_history;
    }


}
