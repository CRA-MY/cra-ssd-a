package hardware;

import java.io.*;

public class SSDFileReader {
    private static final String DEFAULT_VALUE = "0x00000000";
    private static final String STORE_FILE_NAME = "nand.txt";

    public String read(int position) {
        if(storeFileNotExists())
            return writeResultFile(DEFAULT_VALUE);

        try {
            // nand.txt 에서 해당 위치 읽기
            FileReader fileReader = new FileReader(STORE_FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String result = "";
            for(int i=0; i<=position; i++)
                result = bufferedReader.readLine();
            bufferedReader.close();

            // result.txt에 쓰기
            FileWriter fileWriter = new FileWriter("result.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(result);
            bufferedWriter.close();
            
            return result;

        } catch (FileNotFoundException e) {
            return DEFAULT_VALUE;
        } catch (IOException e) {
            return DEFAULT_VALUE;
        }
    }

    private boolean storeFileNotExists() {
        return !new File(STORE_FILE_NAME).exists();
    }

    private String readNandFile(int position) {

        return DEFAULT_VALUE;
    }

    private String writeResultFile(String value) {
        try {
            FileWriter fileWriter = new FileWriter("result.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(value);
            bufferedWriter.close();
            return value;
        } catch (IOException e) {
            return DEFAULT_VALUE;
        }
    }
}
