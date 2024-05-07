package hardware;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SSDFileWriter {

    private static final String RESULT_FILE_NAME = "result.txt";
    private static final String STORE_FILE_NAME = "nand.txt";

    public static void writeNandFile(int position, String value) {
        try {
            ArrayList<String> base_list = SSDFileReader.readNandAllContents();
            base_list.set(position, value);
            writeFile(STORE_FILE_NAME, base_list);
        }
        catch (IOException e) {}
    }

    private static void writeFile(String fileName, ArrayList<String> lines) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for(int i=0; i<lines.size(); i++)
            bufferedWriter.write(lines.get(i) + "\n");
        bufferedWriter.close();
    }

    public static void writeResultFile(String value) throws IOException {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(value));
        writeFile(RESULT_FILE_NAME, list);
    }
}
