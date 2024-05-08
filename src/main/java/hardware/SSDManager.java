package hardware;

import java.util.ArrayList;

public class SSDManager implements IStorage {
    CommandBuffer buffer;
    ArrayList<String> command = new ArrayList<>();

    @Override
    public ArrayList<String> getCommand() {
        return command;
    }

    @Override
    public void setCommand(ArrayList<String> command) {
        this.command = command;
    }

    public SSDManager() {
        this.buffer = new CommandBuffer();
    }

    @Override
    public String run(){

        try {
            String mainCommand = this.command.get(0);
            switch (mainCommand) {
                case "R":
                    return buffer.read(command.get(0) + " " + command.get(1));
                case "W":
                    buffer.write(command.get(0) + " " + command.get(1) + " " + command.get(2));
                    buffer.recontruction();
                    break;
                case "E":
                    buffer.erase(command.get(0) + " " + command.get(1) + " " + command.get(2));
                    buffer.recontruction();
                    break;
                default:
            }
        } catch (Exception e) {

        }
        return null;
    }
}
