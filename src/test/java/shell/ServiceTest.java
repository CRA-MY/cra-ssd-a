package shell;

import hardware.IStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ServiceTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    IStorage iStorage;

    Service service;
    int position = 1;
    String value = "0xAAAABBBB";

    @BeforeEach
    void setUp() {
        iStorage = mock(IStorage.class);
        service = new Service(iStorage);
        System.setOut(new PrintStream(outputStreamCaptor));

    }

    @Test
    void read_success() {
        service.read(position);
        verify(iStorage, times(1)).Read(position);
    }

    @Test
    void write_success() {
        service.write(position, value);
        verify(iStorage, times(1)).Write(position, value);
    }

    @Test
    void exit_success() {
        service.exit();
    }

    @Test
    void help_success(){
        service.help();
        assertEquals("Help Text\n", outputStreamCaptor.toString().trim());

    }
}