package keyword_processor;

import model.Driver;
import point_system.PointSystem;
import point_system.Present;

import java.util.ArrayList;
import java.util.HashMap;

public class QueryProcessor {
    private PointSystem currentSystem;

    public QueryProcessor() {
        currentSystem = new Present();
    }

    public void changePointSystem(PointSystem pointSystem) {
        currentSystem = pointSystem;
    }

    public HashMap<Driver, Integer> calculateResults(ArrayList<Driver> drivers) {
        return currentSystem.calculateScores(drivers);
    }




}
