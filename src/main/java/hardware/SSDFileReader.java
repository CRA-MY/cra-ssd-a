package hardware;

import java.io.*;

public class SSDFileReader {
    private static final String DEFAULT_VALUE = "0x00000000";
    private static final String STORE_FILE_NAME = "nand.txt";
    private static final String RESULT_FILE_NAME = "result.txt";

    private SSDFileWriter ssdFileWriter;

    public SSDFileReader() {
        ssdFileWriter = new SSDFileWriter();
    }

    public String read(int position){
        try{
            String value = readNandFile(position);
            ssdFileWriter.writeResultFile(value);
            return value;
        } catch (IOException io) {
            return DEFAULT_VALUE;
        }
    }

    private String readNandFile(int position) throws IOException {
        String result = "";
        FileReader fileReader = new FileReader(STORE_FILE_NAME);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        for(int i = 0; i<= position; i++)
            result = bufferedReader.readLine();
        bufferedReader.close();
        return result;
    }


}
