package hardware;

import java.io.File;

public class SSDFileReader {
    public String read(int position) {
        File file = new File("nand.txt");
        if(!file.exists()) {
            return "0x00000000";
        }
        return null;
    }
}
