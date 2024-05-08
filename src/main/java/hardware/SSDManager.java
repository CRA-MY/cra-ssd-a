package hardware;

import java.util.ArrayList;
import java.util.Arrays;

public class SSDManager implements IStorage {
    CommandBuffer buffer;
    ArrayList<String> command = new ArrayList<>();
    //private static final String DEFAULT_VALUE = "0x00000000";
    //private static final int MAX_NAND_INDEX = 100;

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

//    public SSDManager(CommandBuffer buffer) {
//        this.buffer = buffer;
//    }

    /*@Override
    public String read(int position) {
        return buffer.read(position);
    }

    @Override
    public void write(int position, String value) {
        buffer.write(position, value);
    }

    @Override
    public void erase(int position, int size) {
        for(int i=position; i<MAX_NAND_INDEX && i-position < size; i++) {
            write(i, DEFAULT_VALUE);
        }
    }*/

    @Override
    public void run(){
        /*if(this.command.get(0).equals("R"))
            System.out.println(this.read(Integer.parseInt(this.command.get(1))));
        if(this.command.get(0).equals("W"))
            this.write(Integer.parseInt(this.command.get(1)), this.command.get(2));
        if(this.command.get(0).equals("E"))
            this.erase(Integer.parseInt(this.command.get(1)), Integer.parseInt(this.command.get(2)));
        */
        try {
            String mainCommand = this.command.get(0);
            switch (mainCommand) {
                case "R":
                    System.out.println(buffer.read(command.get(0) + " " + command.get(1)));
                    break;
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
    }
}
