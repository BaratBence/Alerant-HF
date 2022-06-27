package point_system;

import model.Driver;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class PointSystem {
    Integer[] points;
    //TODO: Multiplier
    public HashMap<Driver, Integer> calculateScores(ArrayList<Driver> drivers) {
        HashMap<Driver, Integer> raceResults = new HashMap<>();
        for(int i=0;i<points.length; i++) {
            raceResults.put(drivers.get(i), points[i]);
        }
        for(int i=points.length; i<drivers.size()-1; i++) {
            raceResults.put(drivers.get(i), 0);
        }
        return raceResults;
    }
}
