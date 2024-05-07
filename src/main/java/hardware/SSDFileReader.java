package hardware;

import java.io.*;
import java.util.ArrayList;

public class SSDFileReader {
    private static final String DEFAULT_VALUE = "0x00000000";
    private static final String STORE_FILE_NAME = "nand.txt";
    private static final int MAX_NAND_INDEX = 100;

    public String read(int position){
        try{
            SSDFileWriter writer = new SSDFileWriter();
            ArrayList<String> value = readNandAllContents();
            writer.writeResultFile(value.get(position));
            return value.get(position);
        } catch (IOException io) {
            return DEFAULT_VALUE;
        }
    }

    public ArrayList<String> readNandAllContents(){
        try {
            ArrayList<String> result = new ArrayList<>();
            FileReader fileReader = new FileReader(STORE_FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for(int i = 0; i< MAX_NAND_INDEX; i++)
                result.add(bufferedReader.readLine());
            return result;
        } catch (IOException e) {
            return getDefaultList();
        }
    }

    private ArrayList<String> getDefaultList() {
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i< MAX_NAND_INDEX; i++)
            result.add(DEFAULT_VALUE);
        return result;
    }
}
