import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PairMain {
    private static ClosestPair cp;
    private static String fileName;
    private static BufferedReader br;
    private static int testNum;
    private static double ptsPoss;

    public static void main(String[] args) {
	//Tests 1-4 use a basic grid
	fileName = "basic_grid_4.txt";
	double score = 0.0;

	//Test 1
	set_cp();
	testNum = 1;
	ptsPoss = 4.0;
	score += test(3, 0, 3, 3);
	//Test 2
	set_cp();
	testNum++;
	score += test(1, 4, 6, 1);
	//Test 3
	set_cp();
	testNum++;
	score += test(0, 4, -1, -1);
	//Test 4
	set_cp();
	testNum++;
	score += test(6, 6, -1, -1);

	//Tests 5-9 use large grid
	fileName = "large_grid_2.txt";

	//Test 5
	set_cp();
	testNum++;
	score += test(0, 0, 46, 42);

	//Test 6
	set_cp();
	testNum++;
	score += test(3, 40, 12, 46);

	//Test 7
	set_cp();
	testNum++;
	score += test(8, 0, -1, -1);

	//Test 8
	set_cp();
	testNum++;
	score += test(5, 31, -1, -1);

	//Test 9
	set_cp();
	testNum++;
	ptsPoss = 3.0;
	score += test(50, 23, -1, -1);

	System.out.println("Total score: " + score);
    }

    public static void set_cp() {
	cp = new ClosestPair(new Grid(fileName));
    }

    public static double test(int x_i, int y_i, int x_o, int y_o) {
	Loc loc = cp.search(x_i, y_i); 
	if(x_o < 0 || y_o < 0) { 
	    if(loc == null) {
		printMsg(true, x_o, y_o, 0, 0);
		return ptsPoss;
	    }
	    else { 
		printMsg(false, x_o, y_o, loc.row, loc.col);
	    }
	} else {
	    if(loc != null && loc.row == x_o && loc.col == y_o) {
		printMsg(true, x_o, y_o, loc.row, loc.col);
		return ptsPoss;
	    } else {
		printMsg(false, x_o, y_o, loc.row, loc.col);
	    }
	}
	return 0.0;
    }

    private static void printMsg(boolean passed, int xx, int xy, int ax, int ay) {
	if(passed)
	    System.out.println("Test " + testNum + " passed! (+" + ptsPoss + " pts)");
	else {
	    System.out.println("Test " + testNum + " failed!");
	    System.out.println("Expected: (" + xx + ", " + xy + ")");
	    System.out.println("Actual: (" + ax + ", " + ay + ")");
	}
    }
}
    
	