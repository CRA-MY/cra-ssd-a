package hardware;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SSDFileWriterTest {

    @Test
    public void write_result_file() {
        SSDFileWriter ssdFileWriter = new SSDFileWriter();

        assertDoesNotThrow(() -> {
            ssdFileWriter.writeResultFile("0x00000000");
        });
    }

}