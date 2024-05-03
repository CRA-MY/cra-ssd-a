package hardware;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SSDFileReaderTest {

    @Test
    public void nand_txt_file_not_exists() {
        SSDFileReader reader = new SSDFileReader();
        int position = 0;

        String actual = reader.read(position);
        String expected = "0x00000000";

        assertEquals(expected, actual);
    }

    @Test
    public void read_non_write_position() {
        SSDFileReader reader = new SSDFileReader();
        int position = 3;

        String actual = reader.read(position);
        String expected = "0x00000000";

        assertEquals(expected, actual);
    }
}