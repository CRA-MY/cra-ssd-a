package hardware;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SSDFileReaderTest {

//    SSDFileReader reader;
//
//    @BeforeEach
//    public void setUp() {
//        reader = new SSDFileReader();
//    }
//
//    public void makeTestNandFile() {
//        try {
//            FileWriter fileWriter = new FileWriter("nand.txt");
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            for(int i=0; i<100; i++) {
//                if (i == 3)
//                    bufferedWriter.write("0x12345678\n");
//                else
//                    bufferedWriter.write("0x00000000\n");
//            }
//            bufferedWriter.close();
//        } catch (IOException e) {
//
//        }
//    }
//
//    public void deleteTestNandFile() {
//        File file = new File("nand.txt");
//        file.delete();
//    }
//
//    @Test
//    public void nand_txt_file_not_exists() {
//        deleteTestNandFile();
//        String actual = reader.read(0);
//        String expected = "0x00000000";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void read_non_write_position() {
//        makeTestNandFile();
//        String actual = reader.read(0);
//        String expected = "0x00000000";
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void read_write_position() {
//        makeTestNandFile();
//        String actual = reader.read(3);
//        String expected = "0x12345678";
//
//        assertEquals(expected, actual);
//    }
}