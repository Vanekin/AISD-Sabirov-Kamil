import IO.HomeWork;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

public class TestHW {
    @Test
    public void testSortCount() {
        byte[] array = {3, 4, 7, 7, 32, 1, 1, 0};
        byte[] expectedArray = {0, 1, 1, 3, 4, 7, 7, 32};
        byte[] actualArray = IO.HomeWork.sortCount(array);
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testByteCount() {
        byte[] array = {3, 4, 7, 7, 32, 1, 1, 0};
        byte[] expectedArray = new byte[256];
        expectedArray[3 + 128] = 1;
        expectedArray[4 + 128] = 1;
        expectedArray[7 + 128] = 2;
        expectedArray[32 + 128] = 1;
        expectedArray[1 + 128] = 2;
        expectedArray[0 + 128] = 1;
        byte[] actualArray = IO.HomeWork.countByte(array);
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testCopyFile() {
        try {
            String expected = Files.readString(Paths.get("src/test/testHW.txt"));
            String actual = Files.readString(Paths.get("src/test/testHWCopy.txt"));
            assertEquals(expected, actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
