package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class Race {
    private final String name;
    private final Integer round;
    private final Float multiplier;
    private final HashMap<Integer,Driver> result;

    private Driver fastestLap;

    public Race(String name, Integer round, Float multiplier) {
        this.name = name;
        this.round = round;
        this.multiplier = multiplier;
        result = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Integer getRound() {
        return round;
    }

    public Float getMultiplier() {
        return multiplier;
    }

    public HashMap<Integer, Driver> getResult() {
        return result;
    }

    public void setFastestLap(Driver fastestLap) {
        this.fastestLap = fastestLap;
    }

    public Driver getFastestLap() {
        return fastestLap;
    }

    public ArrayList<Driver> driversList() {
        ArrayList<Driver> drivers = new ArrayList<>();
        TreeMap<Integer,Driver> tm=new  TreeMap<Integer,Driver>(result);
        for (Integer integer : tm.keySet()) {
            int key = (int) integer;
            drivers.add(result.get(key));
        }
        return drivers;
    }
}
