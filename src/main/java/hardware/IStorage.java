package hardware;

import java.util.ArrayList;

public interface IStorage {
    ArrayList<String> command = new ArrayList<>();
    public ArrayList<String> getCommand();
    public void setCommand(ArrayList<String> command);
    public String read(int position);
    public void write(int position, String value);
    public void erase(int position, int size);
    public void run();
}
