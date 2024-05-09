package hardware;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SSDFileWriterTest {

    SSDFileWriter writer;

    @BeforeEach
    public void setUp() {
        writer = new SSDFileWriter();
    }

    @Test
    public void write_result_file() {
        assertDoesNotThrow(() -> {
            writer.writeResultFile("0x00000000");
        });
    }

    @Test
    public void write_nand_file() {
        int position = 0;
        String value = "0x12345678";

        writer.writeNandFile(position, value);
    }

}