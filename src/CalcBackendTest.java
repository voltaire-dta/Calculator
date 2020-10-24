// This is a test program that verifies the behavior of your calculator
// backend. Add more test-cases to cover the features you add.
// It enters values into the calc backend and verifies that it will report
// the correct display value.
public class CalcBackendTest {

    public static void main(String[] args)
    {
        testEquals();
        testPlus();
        testMinus();
        testTimes();
        testDividedBy();
        testDecimal();
        testManyEntries();
        testClear();
        report();
    }

    private static void testEquals()
    {
        CalcBackend be = new CalcBackend();
        be.feedChar('5');
        assertEquals(5d+"", be.getDisplayVal(), "entered 5");
        be.feedChar('5');
        assertEquals(55d+"", be.getDisplayVal(), "entered 55");
        be.feedChar('=');
        assertEquals(55d+"", be.getDisplayVal(), "entered 55=");
        be.feedChar('=');
        assertEquals(55d+"", be.getDisplayVal(), "entered 55==");
        be.feedChar('=');
        assertEquals(55d+"", be.getDisplayVal(), "entered 55===");
    }

    private static void testPlus()
    {
        CalcBackend be = new CalcBackend();
        be.feedChar('3');
        assertEquals(3d+"", be.getDisplayVal(), "entered 3");
        be.feedChar('+');
        assertEquals(3d+"", be.getDisplayVal(), "entered 3+");
        be.feedChar('9');
        assertEquals(9d+"", be.getDisplayVal(), "entered 3+9");
        be.feedChar('+');
        assertEquals(12d+"", be.getDisplayVal(), "entered 3+9+");
        be.feedChar('2');
        assertEquals(2d+"", be.getDisplayVal(), "entered 3+9+2");
        be.feedChar('=');
        assertEquals(14d+"", be.getDisplayVal(), "entered 3+9+2=");
    }


    private static void testMinus()
    {
        CalcBackend be = new CalcBackend();
        be.feedChar('1');
        assertEquals(1d+"", be.getDisplayVal(), "entered 1");
        be.feedChar('-');
        assertEquals(1d+"", be.getDisplayVal(), "entered 1-");
        be.feedChar('9');
        assertEquals(9d+"", be.getDisplayVal(), "entered 1-9");
        be.feedChar('=');
        assertEquals(-8d+"", be.getDisplayVal(), "entered 1-9=");
    }

    private static void testTimes()
    {
        CalcBackend be = new CalcBackend();
        be.feedChar('2');
        assertEquals(2d+"", be.getDisplayVal(), "entered 2");
        be.feedChar('1');
        assertEquals(21d+"", be.getDisplayVal(), "entered 21");
        be.feedChar('5');
        assertEquals(215d+"", be.getDisplayVal(), "entered 215");
        be.feedChar('*');
        assertEquals(215d+"", be.getDisplayVal(), "entered 215*");
        be.feedChar('3');
        assertEquals(3d+"", be.getDisplayVal(), "entered 215*3");
        be.feedChar('=');
        assertEquals(645d+"", be.getDisplayVal(), "entered 215*3=");
    }


    private static void testDividedBy()
    {
        CalcBackend be = new CalcBackend();
        be.feedChar('3');
        assertEquals(3d+"", be.getDisplayVal(), "entered 3");
        be.feedChar('1');
        assertEquals(31d+"", be.getDisplayVal(), "entered 31");
        be.feedChar('/');
        assertEquals(31d+"", be.getDisplayVal(), "entered 31/");
        be.feedChar('1');
        assertEquals(1d+"", be.getDisplayVal(), "entered 31/1");
        be.feedChar('0');
        assertEquals(10d+"", be.getDisplayVal(), "entered 31/10");
        be.feedChar('=');
        assertEquals(3.1d+"", be.getDisplayVal(), "entered 31/10=");
    }

    private static void testDecimal()
    {
        CalcBackend be = new CalcBackend();
        be.feedChar('3');
        assertEquals(3d+"", be.getDisplayVal(), "entered 3");
        be.feedChar('.');
        assertEquals(3d+"", be.getDisplayVal(), "entered 3.");
        be.feedChar('1');
        assertEquals(3.1d+"", be.getDisplayVal(), "entered 3.1");
        be.feedChar('4');
        assertEquals(3.14d+"", be.getDisplayVal(), "entered 3.14");
        be.feedChar('*');
        assertEquals(3.14d+"", be.getDisplayVal(), "entered 3.14");
        be.feedChar('2');
        assertEquals(2d+"", be.getDisplayVal(), "entered 3.14*2");
        be.feedChar('=');
        assertEquals(6.28d+"", be.getDisplayVal(), "entered 3.14*2=");
    }

    private static void testManyEntries()
    {
        CalcBackend be = new CalcBackend();
        feedChars(be, "1+2*3+22/10=");
        assertEquals(3.1d+"", be.getDisplayVal(), "entered 1+2*3+22/10");
    }

    private static void testClear()
    {
        CalcBackend be = new CalcBackend();
        be.feedChar('5');
        assertEquals(5d+"", be.getDisplayVal(), "entered 5");
        be.feedChar('+');
        assertEquals(5d+"", be.getDisplayVal(), "entered 5+");
        be.feedChar('5');
        assertEquals(5d+"", be.getDisplayVal(), "entered 5+5");
        be.feedChar('=');
        assertEquals(10d+"", be.getDisplayVal(), "entered 5+5=");
        be.feedChar('c');
        assertEquals(0d+"", be.getDisplayVal(), "entered 5+5=c");
    }

    private static void assertEquals(String left, String right, String msg)
    {
        System.out.printf("%-30s ", "test: " + msg);
        if (Math.abs(Double.parseDouble(left) - Double.parseDouble(right) ) > 1E-10) {
            System.out.println("ERROR! " + left + " != " + right);
            testsFailed++;
        } else {
            System.out.println("OK " + left + " == " + right);
            testsPassed++;
        }
    }

    private static void feedChars(CalcBackend b, String commands)
    {
        for (char c : commands.toCharArray()) {
            b.feedChar(c);
        }
    }

    private static void report()
    {
        System.out.println(testsPassed + " tests PASSED");
        if (testsFailed != 0) {
            System.out.println(testsFailed + " tests FAILED");
        }
    }

    private static int testsPassed = 0;
    private static int testsFailed = 0;

}
