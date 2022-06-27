import exception.*;
import keyword_processor.QueryProcessor;
import model.Driver;
import model.Race;
import model.Season;
import point_system.Classic;
import point_system.Modern;
import point_system.NewSystem;
import point_system.Present;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class CommandProcessor {
    private final ArrayList<Season> seasons;
    //private final RaceProcessor raceProcessor;
    private final QueryProcessor queryProcessor;

    public CommandProcessor() {
        //raceProcessor = new RaceProcessor();
        queryProcessor = new QueryProcessor();
        seasons = new ArrayList<>();
    }

    public void processFile(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String[] command;
        try {
            while((line = br.readLine()) != null) {
                command = processCommand(line);
                switch (command[0]) {
                    case "RACE":
                        //checks if params are provided
                        if(command.length != 5) {
                            br.close();
                            throw new WrongArgumentCountException();
                        }

                        //checks if the multiplier is not 0,1,0.5,2
                        float multiplier = Float.parseFloat(command[4]);
                        if(multiplier != 0 && multiplier != 1 && multiplier != 0.5 && multiplier != 2) {
                            br.close();
                            throw new InvalidMultiplierException();
                        }

                        //checks if season is new
                        //Season currentSeason
                        if(findSeason(Integer.parseInt(command[1])) == null) {
                            seasons.add(new Season(Integer.parseInt(command[1])));
                        }
                        /*if(!containsSeason(Integer.parseInt(command[1]))) {
                            seasons.add(new Season(Integer.parseInt(command[1])));
                        }*/

                        //TODO: SHOULD CHECK IF THE ROUND IS THE SAME
                        Race race = new Race(command[2], Integer.parseInt(command[3]), multiplier);
                        findSeason(Integer.parseInt(command[1])).getRaces().add(race);
                        String result;
                        while(!(result = br.readLine()).equals("FINISH")) {
                            command = processCommand(result);
                            if(command[0].equals("RESULT")) {
                                if(race.getResult().get(Integer.parseInt(command[1])) !=null) {
                                    br.close();
                                    throw new DuplicatedFinishingPositionException();
                                }
                                race.getResult().put(Integer.parseInt(command[1]),new Driver(command[2], command[3]));
                            }
                            else if(command[0].equals("FASTEST")) race.setFastestLap(new Driver(command[1], command[2]));
                            //checks if the every command is one of the defined
                            else {
                                br.close();
                                throw new InvalidCommandException();
                            }

                        }
                        break;
                    case "QUERY":
                        if(command.length !=2  && command.length !=3) {
                            br.close();
                            throw new WrongArgumentCountException();
                        }

                        //checks if the season exists
                        Season currentSeason = findSeason(Integer.parseInt(command[1]));
                        if(currentSeason == null) {
                            br.close();
                            throw new NotExistingSeasonException();
                        }
                        int round = -1;
                        if(command.length == 3) {
                            if(Integer.parseInt(command[2]) <=0) {
                                br.close();
                                throw new NoSuchRoundException();
                            }
                            else {
                                round = Integer.parseInt(command[2]);
                            }
                        }

                        br.mark(0);
                        line = br.readLine();
                        command = processCommand(line);
                        if(!command[0].equals("POINT")) {
                            br.reset();
                        } else {
                            if(command.length != 2) {
                                br.close();
                                throw new WrongArgumentCountException();
                            }
                            switch (command[1]) {
                                case "CLASSIC":
                                    queryProcessor.changePointSystem(new Classic());
                                    break;
                                case "MODERN":
                                    queryProcessor.changePointSystem(new Modern());
                                    break;
                                case "NEW":
                                    queryProcessor.changePointSystem(new NewSystem());
                                    break;
                                case "PRESENT":
                                    queryProcessor.changePointSystem(new Present());
                                    break;
                            }
                        }
                        HashMap<Driver, Integer> standing = new HashMap<>();
                        if(round == -1) {

                        }else {
                            for(Race currentRace : currentSeason.getRaces()) {
                                if(currentRace.getRound() <= round) {
                                    if(standing.isEmpty()) standing = queryProcessor.calculateResults(currentRace.driversList());
                                    else {
                                        updateStandings(queryProcessor.calculateResults(currentRace.driversList()), standing);
                                    }
                                }
                            }
                        }
                        showOutPut(standing);
                        break;
                    case "POINT":
                    case "RESULT":
                    case "FINISH":
                    case "FASTEST":
                        br.close();
                        throw new InvalidCommandCallException();

                    default:
                        break;
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer/Float.");
        } catch (InvalidCommandException |
                 DuplicatedFinishingPositionException |
                 InvalidMultiplierException |
                 InvalidCommandCallException |
                 NotExistingSeasonException |
                 NoSuchRoundException |
                 WrongArgumentCountException e) {
            System.out.println(e.toString());
        }
    }

    private String[] processCommand(String command) {
        return command.split(";");
    }

    private boolean containsSeason(Integer year) {
        if(seasons.size() == 0) return false;
        for(Season season : seasons) {
            if(season.getYear().equals(year)) {
                return true;
            }
        }
        return false;
    }

    private Season findSeason(Integer year){
        if(seasons.size() == 0) return null;
        for(Season season: seasons) {
            if(season.getYear().equals(year)) return season;
        }
        return null;
    }

    private void updateStandings(HashMap<Driver, Integer> raceResults,HashMap<Driver, Integer> standings ) {
        for(Driver driver: standings.keySet()) {
            for(Driver resultDriver : raceResults.keySet()) {
                if(driver.getName().equals(resultDriver.getName())) {
                    standings.put(driver, standings.get(driver) + raceResults.get(resultDriver));
                }
            }
        }
    }

    private void showOutPut(HashMap<Driver, Integer> standings ) {
        for(Driver driver: standings.keySet()) {
            System.out.println(driver.getName() + " " + standings.get(driver));
        }

    }

}
