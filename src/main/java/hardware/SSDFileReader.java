package hardware;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SSDFileReader {
    private static final String DEFAULT_VALUE = "0x00000000";
    private static final String STORE_FILE_NAME = "nand.txt";

    public String read(int position) {
        if(storeFileNotExists())
            return writeResultFile(DEFAULT_VALUE);
        return null;
    }

    private boolean storeFileNotExists() {
        return !new File(STORE_FILE_NAME).exists();
    }

    private String writeResultFile(String value) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("result.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(value);
            bufferedWriter.close();
            return value;
        } catch (IOException e) {
            return DEFAULT_VALUE;
        }
    }
}
