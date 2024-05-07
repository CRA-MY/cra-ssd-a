package hardware;

import java.io.*;
import java.util.ArrayList;

public class SSDFileReader {
    private static final String DEFAULT_VALUE = "0x00000000";
    private static final String STORE_FILE_NAME = "nand.txt";

    public static String read(int position){
        try{
            String value = readNandFile(position);
            SSDFileWriter.writeResultFile(value);
            return value;
        } catch (IOException io) {
            return DEFAULT_VALUE;
        }
    }

    private static String readNandFile(int position){
        try {
            String result = "";
            FileReader fileReader = new FileReader(STORE_FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (int i = 0; i <= position; i++)
                result = bufferedReader.readLine();
            bufferedReader.close();
            return result;
        } catch (IOException e) {
            return DEFAULT_VALUE;
        }
    }


    public static ArrayList<String> readNandAllContents(){
        try {
            ArrayList<String> result = new ArrayList<>();
            FileReader fileReader = new FileReader(STORE_FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for(int i = 0; i< 100; i++)
                result.add(bufferedReader.readLine());
            return result;
        } catch (IOException e) {
            ArrayList<String> result = new ArrayList<>();
            for(int i = 0; i< 100; i++)
                result.add(DEFAULT_VALUE);
            return result;
        }
    }
}
