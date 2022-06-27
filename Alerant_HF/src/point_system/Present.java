package point_system;

import model.Driver;

import java.util.ArrayList;
import java.util.HashMap;

public class Present extends PointSystem{
    public Present() {
        points = new Integer[]{25,18,15,12,10,8,6,4,2,1};
    }
    //TODO: MULTIPLIER
    @Override
    public HashMap<Driver, Integer> calculateScores(ArrayList<Driver> drivers) {
        HashMap<Driver, Integer> raceResults = new HashMap<>();
        for(int i=0;i<points.length; i++) {
            raceResults.put(drivers.get(i), points[i]);
        }
        for(int i=points.length; i<drivers.size()-2; i++) {
            raceResults.put(drivers.get(i), 0);
        }
        if(raceResults.containsKey(drivers.get(drivers.size() - 1))) {
            raceResults.put(drivers.get(drivers.size() - 1) ,raceResults.get(drivers.get(drivers.size() - 1)) +1);
        } else {
            raceResults.put(drivers.get(drivers.size() - 1), 1);
        }
        return raceResults;
    }

}
