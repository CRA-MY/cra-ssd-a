package hardware;

import common.Logger;

import java.io.*;
import java.util.ArrayList;

public class SSDFileReader {
    private static final String DEFAULT_VALUE = "0x00000000";
    private static final String STORE_FILE_NAME = "storage/nand.txt";
    private static final int MAX_NAND_INDEX = 100;

    private final Logger logger = Logger.getInstance();

    public String readSpecificPosition(int position){
        try{
            SSDFileWriter writer = new SSDFileWriter();
            //ArrayList<String> value = readNandAllContents();
            ArrayList<String> value = read(STORE_FILE_NAME);
            writer.writeResultFile(value.get(position));
            logger.log("Read " + STORE_FILE_NAME + ", position: " + position);
            return value.get(position);
        } catch (IOException io) {
            return DEFAULT_VALUE;
        }
    }

    public ArrayList<String> read(String fileName) {
        try{
            ArrayList<String> result = new ArrayList<>();
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String input="";
            while((input = bufferedReader.readLine()) != null && !input.isEmpty()) {
                result.add(input);
            }
            logger.log("Read " + fileName + ", position: ALL");
            return result;
        } catch (IOException io) {
            return getDefaultList();
        }
    }

    public ArrayList<String> readNandAllContents(){
//        try {
//            ArrayList<String> result = new ArrayList<>();
//            FileReader fileReader = new FileReader(STORE_FILE_NAME);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            for(int i = 0; i< MAX_NAND_INDEX; i++) {
//                String read_line = bufferedReader.readLine();
//                result.add((read_line == null) ? DEFAULT_VALUE : read_line);
//            }
            return read(STORE_FILE_NAME);
//        } catch (IOException e) {
//            return getDefaultList();
//        }
    }

    private ArrayList<String> getDefaultList() {
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i< MAX_NAND_INDEX; i++)
            result.add(DEFAULT_VALUE);
        logger.log("Read Default List");
        return result;
    }
}
