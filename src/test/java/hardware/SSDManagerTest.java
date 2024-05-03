package hardware;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SSDManagerTest {

    @Mock
    SSDFileWriter ssdFWMock;

    @Mock
    SSDFileReader ssdFRMock;

    @Test
    void write() {
        SSDManager ssdmanager = new SSDManager(ssdFRMock, ssdFWMock);
        boolean result = ssdmanager.Write(20, "0x1289CDEF");
        verify(ssdFWMock, times(1)).write(20, "0x1289CDEF");
    }

    @Test
    void read() {
        SSDManager ssdmanager = new SSDManager(ssdFRMock, ssdFWMock);
        String result = ssdmanager.Read(20);
        verify(ssdFRMock, times(1)).read(20);
    }


}