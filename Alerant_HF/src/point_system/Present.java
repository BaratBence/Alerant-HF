package point_system;

import model.Driver;

import java.util.ArrayList;
import java.util.HashMap;

public class Present extends PointSystem{
    public Present() {
        points = new Integer[]{25,18,15,12,10,8,6,4,2,1};
    }

    @Override
    public HashMap<Driver, Float> calculateScores(ArrayList<Driver> drivers, Float multiplier) {
        HashMap<Driver, Float> raceResults = new HashMap<>();
        for(int i=0;i<points.length; i++) {
            raceResults.put(drivers.get(i), points[i] * multiplier);
        }
        for(int i=points.length; i<drivers.size()-1; i++) {
            raceResults.put(drivers.get(i), 0F);
        }
        for(Driver driver: raceResults.keySet()) {
            if(driver.getName().equals(drivers.get(drivers.size() - 1).getName())) {
                raceResults.put(driver, raceResults.get(driver) + multiplier);
            }
        }
        return raceResults;
    }

}
