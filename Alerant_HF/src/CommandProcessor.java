import exception.*;
import keyword_processor.*;
import model.Driver;
import model.Race;
import model.Season;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CommandProcessor {
    private final ArrayList<Season> seasons;
    private final RaceProcessor raceProcessor;
    private final QueryProcessor queryProcessor;
    private final ResultProcessor resultProcessor;
    private final FastestLapProcessor fastestLapProcessor;

    private final PointProcessor pointProcessor;

    public CommandProcessor() {
        raceProcessor = new RaceProcessor();
        queryProcessor = new QueryProcessor();
        resultProcessor = new ResultProcessor();
        fastestLapProcessor = new FastestLapProcessor();
        pointProcessor = new PointProcessor();
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

                        raceProcessor.verifyCommand(command);

                        //checks if season is new
                        if(findSeason(Integer.parseInt(command[1])) == null) {
                            seasons.add(new Season(Integer.parseInt(command[1])));
                        }

                        //Creating and adding the season
                        Season raceSeason = findSeason(Integer.parseInt(command[1]));
                        Race race = raceProcessor.executeCommand(command, raceSeason);


                        String result;
                        while(!(result = br.readLine()).equals("FINISH")) {
                            command = processCommand(result);
                            if(command[0].equals("RESULT")) {
                                resultProcessor.verifyCommand(command);
                                resultProcessor.executeCommand(command, race);
                            }
                            else if(command[0].equals("FASTEST")) {
                                fastestLapProcessor.verifyCommand(command);
                                fastestLapProcessor.executeCommand(command, race);
                            }
                            //checks if the every command is one of the defined
                            else {
                                throw new InvalidCommandException();
                            }
                        }

                        break;
                    case "QUERY":
                        queryProcessor.verifyCommand(command);
                        String[] queryCommand = command;
                        Season currentSeason = findSeason(Integer.parseInt(command[1]));

                        //check if the next command is POINT and if it is change the current point system
                        br.mark(0);
                        line = br.readLine();
                        command = processCommand(line);
                        if(!command[0].equals("POINT")) {
                            br.reset();
                        } else {
                            pointProcessor.verifyCommand(command);
                            pointProcessor.executeCommand(command, queryProcessor);
                        }

                        //counts the points for each race
                        HashMap<Driver, Float> standing = queryProcessor.executeCommand(queryCommand, currentSeason);

                        //Writes the output on console
                        showOutPut(standing);
                        System.out.println("\n");
                        break;
                    case "POINT":
                    case "RESULT":
                    case "FINISH":
                    case "FASTEST":
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
        } catch (F1Exception e) {
            try {
                br.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(e.toString());
        }
    }

    private String[] processCommand(String command) {
        return command.split(";");
    }

    private Season findSeason(Integer year){
        if(seasons.size() == 0) return null;
        for(Season season: seasons) {
            if(season.getYear().equals(year)) return season;
        }
        return null;
    }


    //Creates the ordered list and prints it
    private void showOutPut(HashMap<Driver, Float> standings)
    {
        List<Map.Entry<Driver, Float> > list = new LinkedList<>(standings.entrySet());
        list.sort(new Comparator<Map.Entry<Driver, Float>>() {
            public int compare(Map.Entry<Driver, Float> o1, Map.Entry<Driver, Float> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        for(Map.Entry<Driver, Float> entry : list) {
            System.out.println(entry.getKey().getName() +  " " + entry.getValue());
        }
    }

}
