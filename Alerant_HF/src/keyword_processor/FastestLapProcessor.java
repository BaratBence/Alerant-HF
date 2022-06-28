package keyword_processor;

import exception.F1Exception;
import exception.WrongArgumentCountException;
import model.Driver;
import model.Race;

public class FastestLapProcessor implements IProcessor{
    @Override
    public void verifyCommand(String[] command) throws F1Exception {
        //checks if params are provided
        if(command.length != 3) {
            throw new WrongArgumentCountException();
        }
    }

    public void executeCommand(String[] command, Race race) {
        race.setFastestLap(new Driver(command[1], command[2]));
    }
}
