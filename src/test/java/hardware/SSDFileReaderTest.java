package hardware;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SSDFileReaderTest {
    @Test
    public void nand_txt_파일이_없는_경우() {
        SSDFileReader reader = new SSDFileReader();
        int position = 0;

        String actual = reader.read(position);
        String expected = "0x00000000";

        assertEquals(expected, actual);
    }
}