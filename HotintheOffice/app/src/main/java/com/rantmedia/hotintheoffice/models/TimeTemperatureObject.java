package com.rantmedia.hotintheoffice.models;

/**
 * Created by ginobasiletti on 28/07/2017.
 */

public class TimeTemperatureObject {
    private long time;
    private double temperature;

    public TimeTemperatureObject(long time, double temperature) {
        this.time = time;
        this.temperature = temperature;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
