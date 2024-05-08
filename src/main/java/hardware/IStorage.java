package hardware;

import java.util.ArrayList;

public interface IStorage {
    ArrayList<String> command = new ArrayList<>();
    ArrayList<String> getCommand();
    void setCommand(ArrayList<String> command);
    String run();
}
