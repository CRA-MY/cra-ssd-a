package hardware;

import java.util.ArrayList;
import java.util.Arrays;

public class SSDManager implements IStorage {
    SSDFileReader ssdFileReader;
    SSDFileWriter ssdFileWriter;
    ArrayList<String> command = new ArrayList<>();
    private static final String DEFAULT_VALUE = "0x00000000";
    private static final int MAX_NAND_INDEX = 100;

    @Override
    public ArrayList<String> getCommand() {
        return command;
    }

    @Override
    public void setCommand(ArrayList<String> command) {
        this.command = command;
    }

    public SSDManager() {
        this.ssdFileReader = new SSDFileReader();
        this.ssdFileWriter = new SSDFileWriter();
    }

    public SSDManager(SSDFileReader ssdFileReader, SSDFileWriter ssdFileWriter) {
        this.ssdFileReader = ssdFileReader;
        this.ssdFileWriter = ssdFileWriter;
    }

    @Override
    public String read(int position) {
        return ssdFileReader.read(position);
    }

    @Override
    public void write(int position, String value) {
        ssdFileWriter.write(position, value);
    }

    @Override
    public void erase(int position, int size) {
        for(int i=position; i<MAX_NAND_INDEX && i-position < size; i++) {
            write(i, DEFAULT_VALUE);
        }
    }

    @Override
    public String run(){
        if(this.command.get(0).equals("R"))
            return this.read(Integer.parseInt(this.command.get(1)));
        if(this.command.get(0).equals("W"))
            this.write(Integer.parseInt(this.command.get(1)), this.command.get(2));
        if(this.command.get(0).equals("E"))
            this.erase(Integer.parseInt(this.command.get(1)), Integer.parseInt(this.command.get(2)));
        return null;
    }
}
