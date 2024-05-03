package hardware;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SSDFileReaderTest {

    SSDFileReader reader;

    @BeforeEach
    public void setUp() {
        reader = new SSDFileReader();
    }

    @Test
    public void nand_txt_file_not_exists() {
        String actual = reader.read(0);
        String expected = "0x00000000";

        assertEquals(expected, actual);
    }

    @Test
    public void read_non_write_position() {
        String actual = reader.read(0);
        String expected = "0x00000000";

        assertEquals(expected, actual);
    }

    @Test
    public void read_write_position() {
        String actual = reader.read(3);
        String expected = "0x12345678";

        assertEquals(expected, actual);
    }
}