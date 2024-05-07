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
    void help_success() {
        service.help();
        String expectedHelp = "read position : 위치(position) 입력 시 read\n";
        expectedHelp += "write position value : 위치(position) 입력값(value) 입력시 write\n";
        expectedHelp += "exit : 종료\n";
        expectedHelp += "fullread : 전체 read\n";
        expectedHelp += "fullwrite value : 입력값(value) 전체 write\n";
        expectedHelp += "testapp1 : full write와 full read Test\n";
        expectedHelp += "testapp2 : write와 overwrte, read Test\n";
        assertEquals(expectedHelp, outputStreamCaptor.toString());
    }

    @Test
    void fullwrite_success() {
        service.fullwrite(value);
        verify(iStorage, times(1)).Write(position, value);
        verify(iStorage, times(100)).Write(anyInt(), anyString());
    }

    @Test
    void fullread_success() {
        service.fullread();
        verify(iStorage, times(100)).Read(anyInt());
    }

}