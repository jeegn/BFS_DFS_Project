import java.util.*;
import java.io.*;

public class QueueMain {
    private static Random gen;
    public static void main(String[] args) {
	QueueMain.init_random();
	double total = 0.0;
	//Test 1
	double score = QueueMain.test1();
	total += score;
	System.out.println("Test 1 Score: " + score);
	//Test 2
	score = QueueMain.test2();
	total += score;
	System.out.println("Test 2 Score: " + score);
	//Test 3
	score = QueueMain.test3(2);
	if(score < 1.0) 
	    total = 0.0;
	else
	    total += score;
	System.out.println("Test 3 Score: " + score); 
	System.out.println("Part 2 Total Score: " + total);
    }

    public static void init_random() {
	gen = new Random(System.currentTimeMillis());
    }
    
    public static double test1() {
	double score = 0;
	ArrayList<String> list = new ArrayList<String>();
	Queue<String> queue = new Queue<String>();
	//test empty queue
	if(queue.isEmpty())
	    score += 0.25;
	try {
	    queue.dequeue();
	} catch (EmptyQueueException e) {
	    score += 0.5;
	}
	try {
	    queue.peek();
	} catch (EmptyQueueException e) {
	    score += 0.5;
	}
	if(queue.size() == 0)
	    score += 0.25;

	if(score < 1.5) 
	    System.out.println("Check basic operations on an empty queue.");
	
	//enqueue random items
       	for(int i = 0; i < gen.nextInt(100) + 50; i++) {
	    String str = QueueMain.getRandomString();
	    list.add(str);
	    queue.enqueue(str);
	}
	if(!queue.isEmpty()) 
	    score += 0.25;
	else 
	    System.out.println("Check isEmpty");
	if(queue.size() == list.size())
	    score += 0.25;
	else
	    System.out.println("Sizes do not match");
	try {
	    if(queue.peek() == list.get(0))
		score += 0.5;
	    else
		System.out.println("Check peek");
	    if(queue.dequeue() == list.remove(0))
		score += 0.5;
	    else
		System.out.println("Check dequeue");
	    if(queue.size() == list.size())
		score += 0.25;
	    else 
		System.out.println("Sizes do not match");
	    if(queue.isEmpty() == list.isEmpty())
		score += 0.25;
	    else
		System.out.println("isEmpty mismatch: expected-" + list.isEmpty() + ", actual-" + queue.isEmpty());
	} catch (EmptyQueueException e) {
	    System.out.println("The queue should not be empty!");
	    e.printStackTrace();
	}
	if(score < 3.5)
	    System.out.println("Check basic queue operations.");
	
	//check for robustness
	for(int i = 0; i < gen.nextInt(1000) + 1000; i++) {
	    String str = QueueMain.getRandomString();
	    list.add(str);
	    queue.enqueue(str);
	}
	if(queue.size() == list.size())
	    score += 1.0;
	else
	    System.out.println("Sizes don't match after enqueue operations! Expected: " + list.size() + ", Actual: " + queue.size());
	int dequeueCount = gen.nextInt(list.size());
	boolean pass = true;
	try {
	    for(int i = 0; i < dequeueCount; i++) {
		if(list.remove(0) != queue.dequeue()) {
		    pass = false;
		    System.out.println("Dequeued item doesn't match expected.");
		    break;
		}
	    }
	} catch (EmptyQueueException e) {
	    System.out.println("Caught unexpected EmptyQueueException!");
	    pass = false;
	}
	if(queue.size() != list.size()) {
	    pass = false;
	    System.out.println("Sizes don't match after dequeue operations! Expected: " + list.size() + ", Actual: " + queue.size());
	}
	if(pass)
	    score += 1.25;
	else 
	    System.out.println("Check queue with many enqueue/dequeue operations.");
	pass = true;
	while(!list.isEmpty()) {
	    try {
		if(queue.dequeue() != list.remove(0)) {
		    pass = false;
		    System.out.println("Dequeued item doesn't match expected.");
		    break;
		}
	    } catch (EmptyQueueException e) {
		System.out.println("Caught unexpected EmptyQueueException!");
		pass = false;
	    }
	}
	if(!queue.isEmpty()) { 
	    pass = false;
	    System.out.println("Queue should be empty!");
	}
	if(pass)
	    score += 1.25;
	else 
	    System.out.println("Check dequeuing items from a large queue until empty!");
	return score;
    }

    public static double test2() {
        double score = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        Queue<Integer> queue = new Queue<Integer>();
        //test empty queue                                                      
        if(queue.isEmpty())
            score += 0.25;
        try {
            queue.dequeue();
        } catch (EmptyQueueException e) {
            score += 0.5;
        }
        try {
            queue.peek();
        } catch (EmptyQueueException e) {
            score += 0.5;
        }
        if(queue.size() == 0)
            score += 0.25;

        if(score < 1.5)
            System.out.println("Check basic operations on an empty queue.");
	//enqueue random items                                                     
	for(int i = 0; i < gen.nextInt(100) + 50; i++) {
            Integer num = new Integer(gen.nextInt(1000));
	    list.add(num);
            queue.enqueue(num);
        }
        if(!queue.isEmpty())
            score += 0.25;
	else
	    System.out.println("Check isEmpty");
        if(queue.size() == list.size())
            score += 0.25;
	else 
	    System.out.println("Sizes do not match.");
        try {
            if(queue.peek() == list.get(0))
                score += 0.5;
	    else
		System.out.println("Check peek");
            if(queue.dequeue() == list.remove(0))
                score += 0.5;
	    else
		System.out.println("Check dequeue");
            if(queue.size() == list.size())
                score += 0.25;
	    else
		System.out.println("Sizes do not match");
            if(queue.isEmpty() == list.isEmpty())
                score += 0.25;
	    else
		System.out.println("Mismatch on isEmpty");
        } catch (EmptyQueueException e) {
            System.out.println("The queue should not be empty!");
            e.printStackTrace();
        }
        if(score < 3.5)
            System.out.println("Check basic queue operations.");
	//check for robustness                                                  
        for(int i = 0; i < gen.nextInt(1000) + 1000; i++) {
            Integer num = new Integer(gen.nextInt(1000));
            list.add(num);
            queue.enqueue(num);
        }
        if(queue.size() == list.size())
            score += 1.0;
        else
            System.out.println("Sizes don't match after enqueue operations! Expected: " + list.size() + ", Actual: " + queue.size());
        int dequeueCount = gen.nextInt(list.size());
        boolean pass = true;
        try {
            for(int i = 0; i < dequeueCount; i++) {
                if(list.remove(0) != queue.dequeue()) {
                    pass = false;
                    System.out.println("Dequeued item doesn't match expected.");
                    break;
                }
            }
        } catch (EmptyQueueException e) {
            System.out.println("Caught unexpected EmptyQueueException!");
            pass = false;
        }
        if(queue.size() != list.size()) {
            pass = false;
            System.out.println("Sizes don't match after dequeue operations! Expected: " + list.size() + ", Actual: " + queue.size());
        }
        if(pass)
            score += 1.25;
        else
            System.out.println("Check queue with many enqueue/dequeue operations.");
        pass = true;
        while(!list.isEmpty()) {
            try {
                if(queue.dequeue() != list.remove(0)) {
                    pass = false;
                    System.out.println("Dequeued item doesn't match expected.");
                    break;
                }
            } catch (EmptyQueueException e) {
                System.out.println("Caught unexpected EmptyQueueException!");
                pass = false;
            }
        }
        if(!queue.isEmpty()) {
            pass = false;
            System.out.println("Queue should be empty!");
        }
        if(pass)
            score += 1.25;
        else
            System.out.println("Check dequeuing items from a large queue until empty!");
        return score;
    }

    public static double test3 (int num) {
	String iFile = "queue_input_" + num + ".txt";
	String oFile = "queue_output_" + num + ".txt"; 
	BufferedReader reader;
	double score = 0.0;
	Queue<String> queue = new Queue<String>();

	//set up queue from input file
	try {
	    reader = new BufferedReader(new FileReader(iFile));
	    String line = reader.readLine();
	    while(line != null) {
		String[] cmd = line.split(" ");
		if(cmd.length < 1)
		    break;
		if(cmd[0].equals("e")) {
		    queue.enqueue(cmd[1]);
		} else if(cmd[0].equals("d")) {
		    try {
			queue.dequeue();
		    } catch (EmptyQueueException e) {
		    }
		}
		line = reader.readLine();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	//get array and compare to output file
	Object[] array = queue.getArray();
	int i = 0;
	try {
	    reader = new BufferedReader(new FileReader(oFile));
	    String line = reader.readLine();
	    int n = Integer.parseInt(line);
	    if(array.length == n)
		score += 1.0;
	    else {
		System.out.println("Array size incorrect: expected-" + n + ", actual-" + array.length);
		return score;
	    }
	    line = reader.readLine();
	    while(i < array.length && line != null) {
		if((array[i] == null && !line.equals("null")) || (array[i] != null && !line.equals(array[i]))) {
		    System.out.println("Mismatch: expected-" + line + ", actual-" + ((array[i]==null)?"null":array[i]));
		    return score;
		}
		i++;
		line = reader.readLine();
	    }
	    score += 3.0;
	} catch (IOException e) {
	    e.printStackTrace();
	}
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