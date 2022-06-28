package keyword_processor;

import exception.F1Exception;
import exception.NoSuchRoundException;
import exception.NotExistingSeasonException;
import exception.WrongArgumentCountException;
import model.Driver;
import model.Race;
import model.Season;
import point_system.PointSystem;
import point_system.Present;

import java.util.ArrayList;
import java.util.HashMap;

public class QueryProcessor implements IProcessor {
    private PointSystem currentSystem;
    private Integer round = -1;
    @Override
    public void verifyCommand(String[] command) throws F1Exception {
        //checks if params are provided
        if(command.length !=2  && command.length !=3) {
            throw new WrongArgumentCountException();
        }
    }
    public HashMap<Driver, Float> executeCommand(String[] command, Season season) throws NoSuchRoundException, NotExistingSeasonException {
        validate(command, season);

        HashMap<Driver, Float> standing = new HashMap<>();
        for (Race currentRace : season.getRaces()) {
            ArrayList<Driver> finish = currentRace.driversList();
            finish.add(currentRace.getFastestLap());

            if(round != -1) {
                if(currentRace.getRound() <= round) {
                    if(standing.isEmpty()) standing = currentSystem.calculateScores(finish, currentRace.getMultiplier());
                    else {
                        updateStandings(currentSystem.calculateScores(finish, currentRace.getMultiplier()), standing);
                    }
                }
            } else {
                if(standing.isEmpty()) standing = currentSystem.calculateScores(finish, currentRace.getMultiplier());
                else {
                    updateStandings(currentSystem.calculateScores(finish, currentRace.getMultiplier()), standing);
                }
            }

        }

        return standing;
    }
    private void validate(String[] command, Season season) throws NotExistingSeasonException, NoSuchRoundException {
        //checks if the season exists
        if(season == null) {
            throw new NotExistingSeasonException();
        }

        //checks if there is a round argument
        if(command.length == 3) {
            if(Integer.parseInt(command[2]) <=0) {
                throw new NoSuchRoundException();
            }
            else {
                round = Integer.parseInt(command[2]);
            }
        }
    }

    private void updateStandings(HashMap<Driver, Float> raceResults,HashMap<Driver, Float> standings ) {
        boolean notNewDriver = false;
        for(Driver resultDriver: raceResults.keySet()) {
            for(Driver driver : standings.keySet()) {
                if(driver.getName().equals(resultDriver.getName())) {
                    standings.put(driver, standings.get(driver) + raceResults.get(resultDriver));
                    notNewDriver = true;
                }
            }
            if(!notNewDriver) {
                standings.put(resultDriver, raceResults.get(resultDriver));
            }
            notNewDriver = false;
        }
    }


    public QueryProcessor() {
        currentSystem = new Present();
    }

    public void changePointSystem(PointSystem pointSystem) {
        currentSystem = pointSystem;
    }

}
