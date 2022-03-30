import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Testing {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void test() {
        int[] args = {4,4,1,1,1};
        String str = "maze-dense.txt";
        Main.mainCorrect(args, str);
        String expected = outContent.toString();
        outContent.reset();

        Main.mainOriginal(args, str);
        String actual = outContent.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testRand() {
        int seed = 69, size = 2000;

        StopWatch sw = new StopWatch();

        sw.start();
        Main.mainRandCorrect(seed, size);
        sw.stop();
        System.out.println("Time taken: " + sw.getTime());
        String expected = outContent.toString();

        outContent.reset();
        sw.reset();

        sw.start();
        Main.mainRandOriginal(seed, size);
        sw.stop();
        System.out.println("Time taken: " + sw.getTime());
        String actual = outContent.toString();

        assertEquals(expected, actual);
    }
}