import java.io.*;

public class Main {
    public static void main(String [] args) {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("input-hf.txt");
        if(inputStream!=null) {
            CommandProcessor cp = new CommandProcessor();
            cp.processFile(inputStream);
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
