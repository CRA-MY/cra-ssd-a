package hardware;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandBuffer {
    private static final int MAX_BUFFER_SIZE = 10;
    private static final int MAX_NAND_INDEX = 100;
    private static final String DEFAULT_VALUE = "0x00000000";
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
        writer.initFile(BUFFER_FILE_NAME);
    }

    public void recontruction () throws IOException {
        ArrayList<String> buff_data = reader.read(BUFFER_FILE_NAME);
        for(int i=buff_data.size()-1; i>=0; i--) {
            String[] commands = convertCommands(buff_data.get(i));
            // 재구축 알고리즘 Start
            if(commands[0].equals("W")) {
                // 가장 최근에 있는 W에 대해 과거에 동일 W가 있으면, 과거 W는 삭제한다. (성능 최적화 예시 1)
                for(int j=i-1; j>=0; j--) {
                    String[] current_commands = convertCommands(buff_data.get(j));
                    if(current_commands[0].equals("W") && commands[1].equals(current_commands[1])) {
                        buff_data.remove(j);
                        i--;
                    }
                }
            }
            if(commands[0].equals("E")) {
                // 가장 최근에 있는 E의 범위에 과거 W가 있으면 무시한다. (성능 최적화 예시 2)
                int start_index = Integer.parseInt(commands[1]);
                int end_index = start_index + Integer.parseInt(commands[2]);
                if(end_index > MAX_NAND_INDEX) end_index = MAX_BUFFER_SIZE;
                for(int j=i-1; j>=0; j--) {
                    String[] current_commands = convertCommands(buff_data.get(j));
                    if(current_commands[0].equals("W")) {
                        int current_index = Integer.parseInt(current_commands[1]);
                        if (start_index <= current_index && current_index < end_index) {
                            buff_data.remove(j);
                            i--;
                        }
                    }
                }

                // Erase가 연속 2개일 때 연속적인 Range를 갖는다면 합친다. (성능 최적화 예시 3)
                int j = i - 1;
                if (j >= 0) {
                    String[] current_commands = convertCommands(buff_data.get(j));
                    if(current_commands[0].equals("E")){
                        int current_start = Integer.parseInt(current_commands[1]);
                        int current_end = current_start + Integer.parseInt(current_commands[2]);

                        if(end_index <= current_start || start_index <= current_end) {

                            int new_start = Integer.min(start_index, current_start);
                            int new_size = Integer.max(end_index, current_end) - new_start;
                            String new_message = "E " + String.valueOf(new_start) + " " + String.valueOf(new_size);
                            buff_data.set(i, new_message);
                            buff_data.remove(j);
                            i--;
                        }
                    }
                }
                // 재구축 알고리즘 End
            }
        }
        writer.initFile(BUFFER_FILE_NAME);
        for(String data : buff_data) {
            writeBuffer(data);
        }
    }

    public String read(String command) {
        String result = null;
        String[] read_commands = command.split(" ");

        ArrayList<String> buff_data = reader.read(BUFFER_FILE_NAME);
        for(int i=buff_data.size()-1; i>=0; i--) {
            String[] commands = convertCommands(buff_data.get(i));
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
                        return DEFAULT_VALUE;
                }
            }
        }
        return reader.readSpecificPosition(Integer.parseInt(read_commands[1]));
    }

    private boolean isBufferSizeFull() {
        return reader.read(BUFFER_FILE_NAME).size()>=MAX_BUFFER_SIZE;
    }

    public void writeBuffer(String command) throws IOException {
        checkBufferState();
        writer.write(BUFFER_FILE_NAME, new ArrayList<String>(Arrays.asList(command)), true);
    }

    private void checkBufferState() throws IOException {
        if(isBufferSizeFull())
            flush();
    }
}
