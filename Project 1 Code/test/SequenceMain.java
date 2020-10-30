import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SequenceMain {
    private static Sequence seq;
    private static String fileName;
    private static BufferedReader br;
    private static int testNum;
    private static double ptsPoss;

    public static void main(String[] args) {
	//Tests 1-7 use a basic grid
	fileName = "basic_grid_3.txt";
	double score = 0.0;

	//Test 1
	set_seq();
	testNum = 1;
	ptsPoss = 6.0;
	score += test(0, 0, 9);
	//Test 2
	set_seq();
	testNum++;
	score += test(5, 5, 9);
	//Test 3
	set_seq();
	testNum++;
	ptsPoss = 4.0;
	score += test(0, 0, 10);
	//Test 4
	set_seq();
	testNum++;
	score += test(2, 7, 6);
	//Test 5
	set_seq();
	testNum++;
	ptsPoss = 2.0;
	score += test(4, 4, 2);
	//Test 6
	set_seq();
	testNum++;
	score += test(3, 6, -1);
	//Test 7
	set_seq();
	testNum++;
	score += test(10, 3, 9);

	//Tests 8 & 9 use large grid
	fileName = "large_grid_3.txt";

	//Test 8
	set_seq();
	testNum++;
	ptsPoss = 5.0;
	score += test(8, 5, 211);
	//Test 9
	set_seq();
	testNum++;
	ptsPoss = 4.0;
	score += test(46, 8, 156);

	System.out.println("Total score: " + score);
    }

    public static void set_seq() {
	seq = new Sequence(new Grid(fileName));
    }

    public static double test(int x, int y, int v) {
	runSeq(x, y, v);
	String exp = "";
	if(testNum == 1 || testNum == 2 || testNum == 8) {
	    exp = getExpected(testNum);
	} else if(testNum == 5) {
	    exp = (new Loc(x, y, v)).toString();
	}
	if(!seq.toString().equals(exp)) 
	    printMsg(false, exp, seq.toString());
	else {
	    printMsg(true, null, null);
	    return ptsPoss;
	}
	return 0.0;
    }

    private static String getExpected(int test) {
	String fn = "test" + test + "_output.txt";
	String line = null;
	try {
	    br = new BufferedReader(new FileReader(fn));
	    line = br.readLine();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return line;
    }
   
    private static void printMsg(boolean passed, String exp, String act) {
	if(passed)
	    System.out.println("Test " + testNum + " passed! (+" + ptsPoss + " pts)");
	else {
	    System.out.println("Test " + testNum + " failed!");
	    System.out.println("Expected: " + exp);
	    System.out.println("Actual: " + act);
	}
    }

    private static void runSeq(int x, int y, int v) {
	try {
	    seq.getSeq(x, y, v);
	} catch (EmptyStackException e) {
	    e.printStackTrace();
	}
    }			   
}
    
	