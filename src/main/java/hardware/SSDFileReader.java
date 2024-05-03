package hardware;

import java.io.File;

public class SSDFileReader {
    private static final String DEFAULT_VALUE = "0x00000000";
    private static final String STORE_FILE_NAME = "nand.txt";

    public String read(int position) {
        if(storeFileNotExists())
            return DEFAULT_VALUE;
        return null;
    }

    private boolean storeFileNotExists() {
        return !new File(STORE_FILE_NAME).exists();
    }
}
