package hardware;

import java.io.*;
import java.util.ArrayList;

public class SSDFileReader {
    private static final String DEFAULT_VALUE = "0x00000000";
    private static final String STORE_FILE_NAME = "nand.txt";
    private static final int MAX_NAND_INDEX = 100;

    public static String read(int position){
        try{
            ArrayList<String> value = readNandAllContents();
            SSDFileWriter.writeResultFile(value.get(position));
            return value.get(position);
        } catch (IOException io) {
            return DEFAULT_VALUE;
        }
    }

    public static ArrayList<String> readNandAllContents(){
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

    private static ArrayList<String> getDefaultList() {
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i< MAX_NAND_INDEX; i++)
            result.add(DEFAULT_VALUE);
        return result;
    }
}
