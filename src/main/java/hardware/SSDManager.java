package hardware;

import java.util.ArrayList;

public class SSDManager implements IStorage {
    SSDFileReader ssdFileReader;
    SSDFileWriter ssdFileWriter;
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
        this.ssdFileReader = new SSDFileReader();
        this.ssdFileWriter = new SSDFileWriter();
    }

    public SSDManager(SSDFileReader ssdFileReader, SSDFileWriter ssdFileWriter) {
        this.ssdFileReader = ssdFileReader;
        this.ssdFileWriter = ssdFileWriter;
    }

    @Override
    public String Read(int position) {
        return ssdFileReader.read(position);
    }

    @Override
    public void Write(int position, String value) {
        ssdFileWriter.write(position, value);
    }

    @Override
    public void run(){
        if(this.command.get(0).equals("R")){
            System.out.println(this.Read(Integer.parseInt(this.command.get(1))));
        }else if(this.command.get(0).equals("W")){
            this.Write(Integer.parseInt(this.command.get(1)), this.command.get(2));
        }
    }
}
