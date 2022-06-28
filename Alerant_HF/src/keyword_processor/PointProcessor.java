package keyword_processor;

import exception.F1Exception;
import exception.NoSuchPointSystemException;
import exception.WrongArgumentCountException;
import point_system.Classic;
import point_system.Modern;
import point_system.NewSystem;
import point_system.Present;

public class PointProcessor implements IProcessor{
    @Override
    public void verifyCommand(String[] command) throws F1Exception {
        //checks if params are provided
        if(command.length !=2) {
            throw new WrongArgumentCountException();
        }
    }

    public void executeCommand(String[] command, QueryProcessor queryProcessor) throws F1Exception{
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
            default:
                throw new NoSuchPointSystemException();
        }
    }
}
