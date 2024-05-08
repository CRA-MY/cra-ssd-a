package hardware;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandBuffer {
    private static final int MAX_BUFFER_SIZE = 10;
    private static final String DEFAULT_VALUE = "0x00000000";
    private static final int MAX_NAND_INDEX = 100;
    private static final String BUFFER_FILE_NAME = "storage/buffer.txt";

    SSDFileReader reader = new SSDFileReader();
    SSDFileWriter writer = new SSDFileWriter();

    public String[] convertCommands(String command) {
        return command.split(" ");
    }

    public void flush() throws IOException {
        ArrayList<String> buff_data = reader.read(BUFFER_FILE_NAME);
        for(String data : buff_data) {
            String[] commands = convertCommands(data);
            if(commands[0].equals("W")) {
                // write action
                writer.write(Integer.parseInt(commands[1]), commands[2]);
            }
            if(commands[0].equals("E")) {
                // erase action
                int position = Integer.parseInt(commands[1]);
                int size = Integer.parseInt(commands[2]);
                for(int i=position; i<MAX_NAND_INDEX && i-position < size; i++) {
                    writer.write(i, DEFAULT_VALUE);
                }
            }
        }
        new FileWriter(BUFFER_FILE_NAME, false).close();
    }

    public void recontruction () {
        // 재구축 알고리즘 Start

        // 재구축 알고리즘 End
    }

    public String read(String command) {
//        SSDFileReader reader = new SSDFileReader();
//        return reader.read(position);
        // W 20 0xABCDABCD
        // W 21 0x12341234
        // W 20 0xEEEEFFFF
        // E 20 10
        String result = null;
        String[] read_commands = command.split(" ");

        ArrayList<String> buff_data = reader.read(BUFFER_FILE_NAME);
        for(int i=buff_data.size()-1; i>=0; i--) {
            String data = buff_data.get(i);
            String[] commands = data.split(" ");
            if(commands[0].equals("W")) {
                if(commands[1].equals(read_commands[1])) {
                    return commands[2];
                }
            }
            if(commands[0].equals("E")) {
                int startIdx = Integer.parseInt(commands[1]);
                int size = Integer.parseInt(commands[2]);
                for(int j=startIdx; j<startIdx+size; j++){
                    if(String.valueOf(j).equals(read_commands[1]))
                        return "0x00000000";
                }
            }
        }
        return reader.readSpecificPosition(Integer.parseInt(read_commands[1]));
    }

    private boolean isBufferSizeFull() {
        return reader.read(BUFFER_FILE_NAME).size()>=MAX_BUFFER_SIZE;
    }

    public void write(String command) throws IOException {
        if(isBufferSizeFull())
            flush();
        writer.write(BUFFER_FILE_NAME, new ArrayList<String>(Arrays.asList(command)), true);
    }


    public void erase(String command) throws IOException {
        if(isBufferSizeFull())
            flush();
        writer.write(BUFFER_FILE_NAME, new ArrayList<String>(Arrays.asList(command)), true);
    }
}
