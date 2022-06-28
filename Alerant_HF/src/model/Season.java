package model;

import java.util.ArrayList;

public class Season {
    private final Integer year;
    private final ArrayList<Race> races;

    public Season(Integer year) {
        this.year = year;
        races = new ArrayList<>();
    }

    public Integer getYear() {
        return year;
    }

    public ArrayList<Race> getRaces() {
        return races;
    }

}
