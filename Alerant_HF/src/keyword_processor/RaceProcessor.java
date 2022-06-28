package keyword_processor;

import exception.*;
import model.Race;
import model.Season;

public class RaceProcessor implements IProcessor {
    @Override
    public void verifyCommand(String[] command) throws F1Exception {
        //checks if params are provided
        if(command.length != 5) {
            throw new WrongArgumentCountException();
        }

        //checks if the multiplier is not 0,1,0.5,2
        float multiplier = Float.parseFloat(command[4]);
        if(multiplier != 0 && multiplier != 1 && multiplier != 0.5 && multiplier != 2) {
            try {
                throw new InvalidMultiplierException();
            } catch (InvalidMultiplierException e) {
                System.out.println(e.toString());
            }
        }
    }

    public Race executeCommand(String[] command, Season season) throws InvalidRoundException, RaceAlreadyExistsException {
        validateCommand(command, season);
        Race race = new Race(command[2], Integer.parseInt(command[3]), Float.parseFloat(command[4]));
        season.getRaces().add(race);
        return race;
    }

    private void validateCommand(String[] command, Season season) throws InvalidRoundException, RaceAlreadyExistsException {
        for (Race race : season.getRaces()) {
            //checks if this round already exists
            if(race.getRound() == Integer.parseInt(command[3])) {
                throw new InvalidRoundException();
            }
            //checks if the race already been added
            if(race.getName().equals(command[2])) {
                throw new RaceAlreadyExistsException();
            }
        }
    }
}
