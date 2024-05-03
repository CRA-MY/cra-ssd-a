package shell;

import hardware.IStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ServiceTest {

    IStorage iStorage;

    Service service;
    int position = 1;
    String value = "0xAAAABBBB";

    @BeforeEach
    void setUp() {
        iStorage = mock(IStorage.class);
        service = new Service(iStorage);

    }

    @Test
    void read_success() {
        when(iStorage.Read(position)).thenReturn("Result");
        service.read(position);
        verify(iStorage, times(1)).Read(position);
    }

    @Test
    void write_success() {
        when(iStorage.Write(position, value)).thenReturn(true);
        service.write(position, value);
        verify(iStorage, times(1)).Write(position, value);
    }
}