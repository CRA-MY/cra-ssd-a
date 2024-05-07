package hardware;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SSDFileWriter {
    /*
    1. nand.txt 파일에 내용을 읽어서 ArrayList<String>에 저장한다.
    2. input으로 들어온 position에 값을 value로 바꿔준다.
    3. nana.txt에 변경된 ArrayList<String>로 새로 쓴다.
    */
    private static final String DEFAULT_VALUE = "0x00000000";
    private static final String RESULT_FILE_NAME = "result.txt";
    private static final String STORE_FILE_NAME = "nand.txt";

    public static boolean writeNandFile(int position, String value) {
        try {
            ArrayList<String> base_list = SSDFileReader.readNandAllContents();
            base_list.set(position, value);
            FileWriter fileWriter = new FileWriter(STORE_FILE_NAME);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int i=0; i<base_list.size(); i++)
                bufferedWriter.write(base_list.get(i) + "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static void writeResultFile(String value) throws IOException {
        FileWriter fileWriter = new FileWriter(RESULT_FILE_NAME);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(value);
        bufferedWriter.close();
    }
}
