package keyword_processor;


import exception.DriverAlreadyAddedException;
import exception.DuplicatedFinishingPositionException;
import exception.F1Exception;
import exception.WrongArgumentCountException;
import model.Driver;
import model.Race;


public class ResultProcessor implements IProcessor {

    @Override
    public void verifyCommand(String[] command) throws F1Exception {
        //checks if params are provided
        if(command.length != 4) {
            throw new WrongArgumentCountException();
        }
    }
    public void executeCommand(String[] command, Race race) throws DriverAlreadyAddedException, DuplicatedFinishingPositionException {
        validateCommand(command, race);
        race.getResult().put(Integer.parseInt(command[1]),new Driver(command[2], command[3]));
    }

    private void validateCommand(String[] command, Race race) throws DuplicatedFinishingPositionException, DriverAlreadyAddedException {
        //checks if the position is already exists
        if(race.getResult().get(Integer.parseInt(command[1])) !=null) {
            throw new DuplicatedFinishingPositionException();
        }
        //checks if the driver was already in the processed finishers
        for (Integer position: race.getResult().keySet()) {
            if(race.getResult().get(position).getName().equals(command[2])) {
                throw new DriverAlreadyAddedException();
            }
        }
    }

}
