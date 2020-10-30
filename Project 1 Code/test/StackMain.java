import java.util.*;

public class StackMain {
    private static Random gen;
    public static void main(String[] args) {
	StackMain.init_random();
	double total = 0.0;
	//Test 1
	double score = StackMain.test1();
	total += score;
	System.out.println("Test 1 Score: " + score);
	//Test 2
	score = StackMain.test2();
	total += score;
	System.out.println("Test 2 Score: " + score);
	
	System.out.println("Part 1 Total Score: " + total);
    }

    public static void init_random() {
	gen = new Random(System.currentTimeMillis());
    }
    
    public static double test1() {
	double score = 0;
	ArrayList<String> list = new ArrayList<String>();
	Stack<String> stack = new Stack<String>();
	//test empty stack
	if(stack.isEmpty())
	    score += 0.25;
	try {
	    stack.pop();
	} catch (EmptyStackException e) {
	    score += 0.25;
	}
	try {
	    stack.peek();
	} catch (EmptyStackException e) {
	    score += 0.25;
	}
	if(stack.size() == 0)
	    score += 0.25;

	if(score < 1.0) 
	    System.out.println("Check basic operations on an empty stack.");
	
	//push random items
       	for(int i = 0; i < gen.nextInt(100) + 50; i++) {
	    String str = StackMain.getRandomString();
	    list.add(str);
	    stack.push(str);
	}
	if(!stack.isEmpty())
	    score += 0.25;
	if(stack.size() == list.size())
	    score += 0.25;
	try {
	    if(stack.peek() == list.get(list.size()-1))
		score += 0.5;
	    if(stack.pop() == list.remove(list.size()-1))
		score += 0.5;
	    if(stack.size() == list.size())
		score += 0.25;
	    if(stack.isEmpty() == list.isEmpty())
		score += 0.25;
	} catch (EmptyStackException e) {
	    System.out.println("The stack should not be empty!");
	    e.printStackTrace();
	}
	if(score < 3.0)
	    System.out.println("Check basic stack operations.");
	
	//check for robustness
	for(int i = 0; i < gen.nextInt(1000) + 1000; i++) {
	    String str = StackMain.getRandomString();
	    list.add(str);
	    stack.push(str);
	}
	if(stack.size() == list.size())
	    score += 1.0;
	else
	    System.out.println("Sizes don't match after push operations! Expected: " + list.size() + ", Actual: " + stack.size());
	int popCount = gen.nextInt(list.size());
	boolean pass = true;
	try {
	    for(int i = 0; i < popCount; i++) {
		if(list.remove(list.size()-1) != stack.pop()) {
		    pass = false;
		    System.out.println("Popped item doesn't match expected.");
		    break;
		}
	    }
	} catch (EmptyStackException e) {
	    System.out.println("Caught unexpected EmptyStackException!");
	    pass = false;
	}
	if(stack.size() != list.size()) {
	    pass = false;
	    System.out.println("Sizes don't match after pop operations! Expected: " + list.size() + ", Actual: " + stack.size());
	}
	if(pass)
	    score += 1.0;
	else 
	    System.out.println("Check stack with many push/pop operations.");
	pass = true;
	while(!list.isEmpty()) {
	    try {
		if(stack.pop() != list.remove(list.size()-1)) {
		    pass = false;
		    System.out.println("Popped item doesn't match expected.");
		    break;
		}
	    } catch (EmptyStackException e) {
		System.out.println("Caught unexpected EmptyStackException!");
		pass = false;
	    }
	}
	if(!stack.isEmpty()) { 
	    pass = false;
	    System.out.println("Stack should be empty!");
	}
	if(pass)
	    score += 1.0;
	else 
	    System.out.println("Check popping items from a large stack until empty!");
	return score;
    }

    public static double test2() {
        double score = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        //test empty stack                                                      
        if(stack.isEmpty())
            score += 0.25;
        try {
            stack.pop();
        } catch (EmptyStackException e) {
            score += 0.25;
        }
        try {
            stack.peek();
        } catch (EmptyStackException e) {
            score += 0.25;
        }
        if(stack.size() == 0)
            score += 0.25;

        if(score < 1.0)
            System.out.println("Check basic operations on an empty stack.");
	//push random items                                                     
	for(int i = 0; i < gen.nextInt(100) + 50; i++) {
            Integer num = new Integer(gen.nextInt(1000));
	    list.add(num);
            stack.push(num);
        }
        if(!stack.isEmpty())
            score += 0.25;
        if(stack.size() == list.size())
            score += 0.25;
        try {
            if(stack.peek() == list.get(list.size()-1))
                score += 0.5;
            if(stack.pop() == list.remove(list.size()-1))
                score += 0.5;
            if(stack.size() == list.size())
                score += 0.25;
            if(stack.isEmpty() == list.isEmpty())
                score += 0.25;
        } catch (EmptyStackException e) {
            System.out.println("The stack should not be empty!");
            e.printStackTrace();
        }
        if(score < 3.0)
            System.out.println("Check basic stack operations.");
	//check for robustness                                                  
        for(int i = 0; i < gen.nextInt(1000) + 1000; i++) {
            Integer num = new Integer(gen.nextInt(1000));
            list.add(num);
            stack.push(num);
        }
        if(stack.size() == list.size())
            score += 1.0;
        else
            System.out.println("Sizes don't match after push operations! Expected: " + list.size() + ", Actual: " + stack.size());
        int popCount = gen.nextInt(list.size());
        boolean pass = true;
        try {
            for(int i = 0; i < popCount; i++) {
                if(list.remove(list.size()-1) != stack.pop()) {
                    pass = false;
                    System.out.println("Popped item doesn't match expected.");
                    break;
                }
            }
        } catch (EmptyStackException e) {
            System.out.println("Caught unexpected EmptyStackException!");
            pass = false;
        }
        if(stack.size() != list.size()) {
            pass = false;
            System.out.println("Sizes don't match after pop operations! Expected: " + list.size() + ", Actual: " + stack.size());
        }
        if(pass)
            score += 1.0;
        else
            System.out.println("Check stack with many push/pop operations.");
        pass = true;
        while(!list.isEmpty()) {
            try {
                if(stack.pop() != list.remove(list.size()-1)) {
                    pass = false;
                    System.out.println("Popped item doesn't match expected.");
                    break;
                }
            } catch (EmptyStackException e) {
                System.out.println("Caught unexpected EmptyStackException!");
                pass = false;
            }
        }
        if(!stack.isEmpty()) {
            pass = false;
            System.out.println("Stack should be empty!");
        }
        if(pass)
            score += 1.0;
        else
            System.out.println("Check popping items from a large stack until empty!");
        return score;
    }

    public static String getRandomString() {
        int len = gen.nextInt(10) + 1;
        String str = "";
        for(int i = 0; i < len; i++) {
            int num = gen.nextInt(75) + 48;
            str += Character.toString((char)num);
        }
        return str;
    }

}