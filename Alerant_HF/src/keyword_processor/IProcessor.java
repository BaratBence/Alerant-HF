package keyword_processor;

import exception.F1Exception;

public interface IProcessor {
    public void verifyCommand(String[] command) throws F1Exception;
}
