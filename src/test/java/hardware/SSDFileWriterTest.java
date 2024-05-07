package hardware;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SSDFileWriterTest {

    @Test
    public void write_result_file() {
        SSDFileWriter ssdFileWriter = new SSDFileWriter();

        assertDoesNotThrow(() -> {
            ssdFileWriter.writeResultFile("0x00000000");
        });
    }

    @Test
    public void write_nand_file() {
        SSDFileWriter ssdFileWriter = new SSDFileWriter();

        int position = 0;
        String value = "0x12345678";

        SSDFileWriter.writeNandFile(position, value);
    }

}