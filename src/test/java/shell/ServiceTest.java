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
    Service iservice;
    String testapp2WriteValue = "0xAAAABBBB";
    String testapp2OverWriteValue = "0x12345678";

    @BeforeEach
    void setUp() {
        iStorage = mock(IStorage.class);
        service = new Service(iStorage);
        System.setOut(new PrintStream(outputStreamCaptor));
        iservice = mock(Service.class);
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

    @Test
    void testapp1_fullwrite수행여부확인(){
        for(int i=0;i<100;i++){
            doReturn(value).when(iStorage).Read(i);
        }

        service.testapp1(value);

        verify(iStorage, times(1)).Write(0, value);
        verify(iStorage, times(100)).Write(anyInt(), anyString());
    }

    @Test
    void testapp1_성공시_fullread수행여부및성공출력확인(){
        for(int i=0;i<100;i++){
            doReturn(value).when(iStorage).Read(i);
        }
        String expected = "TestApp1 성공하였습니다.\n";

        service.testapp1(value);

        verify(iStorage, times(100)).Read(anyInt());
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void testapp1_실패시_read수행횟수및실패출력확인(){
        for(int i=0;i<10;i++){
            doReturn(value).when(iStorage).Read(i);
        }
        doReturn("Other Value").when(iStorage).Read(10);
        String expected = "TestApp1 실패.\n";
        expected += "10번 LBA에 0xAAAABBBB가 정상 Write 되지 않았습니다.\n";

        service.testapp1(value);

        verify(iStorage, times(11)).Read(anyInt());
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void testapp2_write수행횟수확인(){
        for(int i=0;i<5;i++){
            doReturn(testapp2OverWriteValue).when(iStorage).Read(i);
        }

        service.testapp2();

        verify(iStorage, times(30)).Write(0, testapp2WriteValue);
        verify(iStorage, times(1)).Write(0, testapp2OverWriteValue);
    }

    @Test
    void testapp2_성공시_read수행횟수및성공출력확인(){
        for(int i=0;i<5;i++){
            doReturn(testapp2OverWriteValue).when(iStorage).Read(i);
        }
        String expected = "TestApp2 성공하였습니다.\n";

        service.testapp2();

        verify(iStorage, times(5)).Read(anyInt());
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void testapp2_실패시_read수행횟수및실패출력확인(){
        for(int i=0;i<3;i++){
            doReturn(testapp2OverWriteValue).when(iStorage).Read(i);
        }
        doReturn("Other Value").when(iStorage).Read(3);
        String expected = "TestApp2 실패.\n";
        expected += "3번 LBA에 0x12345678가 정상 Over Write 되지 않았습니다.\n";

        service.testapp2();

        verify(iStorage, times(4)).Read(anyInt());
        assertEquals(expected, outputStreamCaptor.toString());
    }

}