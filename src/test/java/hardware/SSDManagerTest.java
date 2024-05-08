package hardware;

import jdk.jfr.Enabled;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SSDManagerTest {

//    @Mock
//    SSDFileWriter ssdFWMock;
//
//    @Mock
//    SSDFileReader ssdFRMock;
//
//    @Disabled
//    @Test
//    void write() {
//        SSDManager ssdmanager = new SSDManager(ssdFRMock, ssdFWMock);
//        ssdmanager.Write(20, "0x1289CDEF");
//        verify(ssdFWMock, times(1)).write(20, "0x1289CDEF");
//    }
//
//    @Disabled
//    @Test
//    void read() {
//        SSDManager ssdmanager = new SSDManager(ssdFRMock, ssdFWMock);
//        String result = ssdmanager.Read(20);
//        verify(ssdFRMock, times(1)).read(20);
//    }

    @Test
    public void writeBufferTest() {
        SSDManager manager = new SSDManager();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","1","0x01DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","2","0x02DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","3","0x03DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","4","0x04DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","5","0x05DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","6","0x06DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","7","0x07DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","8","0x08DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","9","0x09DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","10","0x10DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","11","0x11DFASDF")));
        manager.run();
    }

    @Test
    public void eraseBufferTest() {
        SSDManager manager = new SSDManager();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","1","0x01DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","2","0x02DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","3","0x03DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","4","0x04DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","5","0x05DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","6","0x06DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","7","0x07DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","8","0x08DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","9","0x09DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","10","0x10DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("E","1","1")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("E","2","1")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("E","3","1")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("E","4","1")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("E","5","1")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("E","6","1")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("E","7","1")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("E","8","1")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("E","9","1")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("E","10","1")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("E","11","1")));
        manager.run();
    }

    @Test
    public void readBufferTest() {
        SSDManager manager = new SSDManager();
        /*manager.setCommand(new ArrayList<String>(Arrays.asList("W","1","0x01DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","2","0x02DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","3","0x03DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","4","0x04DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("W","5","0x05DFASDF")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("E","3","7")));
        manager.run();*/
        manager.setCommand(new ArrayList<String>(Arrays.asList("R", "2")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("R", "3")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("R", "5")));
        manager.run();
        manager.setCommand(new ArrayList<String>(Arrays.asList("R", "7")));
        manager.run();
    }
}