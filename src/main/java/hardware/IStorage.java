package hardware;

import java.util.ArrayList;

public interface IStorage {
    ArrayList<String> command = new ArrayList<>();
    public ArrayList<String> getCommand();
    public void setCommand(ArrayList<String> command);
    public String Read(int position);
    public void Write(int position, String value);
    public void run();
}
