package hardware;

import common.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SSDFileWriter {

    private static final String RESULT_FILE_NAME = "storage/result.txt";
    private static final String STORE_FILE_NAME = "storage/nand.txt";

    private final Logger logger = Logger.getInstance();

    public void writeNandFile(int position, String value) {
        try {
            SSDFileReader reader = new SSDFileReader();
            ArrayList<String> base_list = reader.readNandAllContents();
            base_list.set(position, value);
            logger.log("Write " + STORE_FILE_NAME + ", position: " + position + ", value: " + value);
            write(STORE_FILE_NAME, base_list, false);
        }
        catch (IOException e) {}
    }

    public void write(String fileName, ArrayList<String> lines, boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName, append);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for(int i=0; i<lines.size(); i++)
            bufferedWriter.write(lines.get(i) + "\n");
        logger.log("Write " + fileName + ", lines: "+lines.size()+", append: " + append);
        bufferedWriter.close();
    }

    public void writeResultFile(String value) throws IOException {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(value));
        write(RESULT_FILE_NAME, list, false);
    }

    public void initFile(String bufferFileName) throws IOException {
        logger.log("Init File " + bufferFileName);
        new FileWriter(bufferFileName, false).close();
    }
}
