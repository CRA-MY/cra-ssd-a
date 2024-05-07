package hardware;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SSDFileWriter {
    /*
    1. nand.txt 파일에 내용일 읽어서 ArrayList<String>에 저장한다.
    2. input으로 들어온 position에 값을 value로 바꿔준다.
    3. nana.txt에 변경된 ArrayList<String>로 새로 쓴다.
    */
    private static final String DEFAULT_VALUE = "0x00000000";
    private static final String RESULT_FILE_NAME = "result.txt";

    public boolean write(int position, String value) {
        return false;
    }

    public void writeResultFile(String value) throws IOException {
        FileWriter fileWriter = new FileWriter(RESULT_FILE_NAME);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(value);
        bufferedWriter.close();
    }
}
