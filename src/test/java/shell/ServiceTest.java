package shell;

import hardware.IStorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class ServiceTest {
    @Mock
    IStorage iStorage;

    Service service = new Service(iStorage);
    int position = 1;
    String value = "0xAAAABBBB";

    @Test
    void read_success(){
        doReturn("Result").when(iStorage).Read(position);
        service.read(position);
        verify(iStorage,times(1)).Read(position);
    }

    @Test
    void write_success(){
        doReturn("Result").when(iStorage).Read(position);
        service.write(position, value);
        verify(iStorage,times(1)).Write(position,value);
    }
}