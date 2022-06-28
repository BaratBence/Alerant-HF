package point_system;

import model.Driver;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class PointSystem {
    Integer[] points;
    public HashMap<Driver, Float> calculateScores(ArrayList<Driver> drivers, Float multiplier) {
        HashMap<Driver, Float> raceResults = new HashMap<>();
        for(int i=0;i<points.length; i++) {
            raceResults.put(drivers.get(i), points[i]*multiplier);
        }
        for(int i=points.length; i<drivers.size()-1; i++) {
            raceResults.put(drivers.get(i), 0F);

        }
        return raceResults;
    }
}
