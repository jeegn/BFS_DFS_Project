public class Queue<Item> {
    private Item[] queue;
    private int front;
    private int back;
    private int size;
    private int initialCapacity;

    //constructor: create an empty Queue with initial
    //capacity of 1
    public Queue() {
	//To be implemented
        Object[] Q = new Object[1];
        queue = (Item[]) Q;
        front = back = 0;
        size = 0;
        initialCapacity = 1;
    }

    //constructor: create an empty Queue with initial
    //capacity of n
    public Queue(int n) {
	//To be implemented
        Object[] Q = new Object[n];
        queue = (Item[]) Q;
        front = back = 0;
        size = 0;
        initialCapacity = n;
    }
    
    //add an item to the back of the Queue
    //double the array capacity if the Queue is full
    public void enqueue(Item item) {
	//To be implemented
        if (size() == queue.length) {
            Object[] doubledArray = new Object[(2 * queue.length)];
            int copyIndex = front;
            int i = 0;
            while (i < size()) {
                doubledArray[i] = queue[copyIndex];
                if (copyIndex == (queue.length - 1))
                    copyIndex = 0;
                else
                    copyIndex++;
                i++;

            }
            queue = (Item[]) doubledArray;
            front = 0;
            back = size() - 1;
            //Array grown
        }
        if (back == (queue.length - 1))
            back = 0;
        else
            back++;
        queue[back] = item;
        size++;
    }

    //remove and return the front item from the Queue
    //throw EmptyQueueException if the Queue is empty
    //reduce the array capacity by half if the size 
    //of the Queue falls below 1/4 full
    public Item dequeue() throws EmptyQueueException {
        //To be implemented
        if (isEmpty())
            throw new EmptyQueueException();
        Item data = queue[front];
        queue[front] = null;
        if (front == (queue.length - 1 ))
            front = 0;
        else
            front++;
        size --;
        if (size() < (queue.length / 4) && ((queue.length / 2) >= initialCapacity)) {
            Object[] halvedArray = new Object[(queue.length / 2)];
            int copyIndex = front;
            int i = 0;
            while (i < size()) {
                halvedArray[i] = queue[copyIndex];
                if (copyIndex == (queue.length - 1))
                    copyIndex = 0;
                else
                    copyIndex++;
                i++;

            }
            queue = (Item[]) halvedArray;
            front = 0;
            back = size() - 1;
            //Array grown
        }
        return data;
    }

    //return true if the Queue is empty
    //return false if the Queue is not empty
    public boolean isEmpty() {
	//To be implemented
        return (size() == 0);
    }

    //return the size of the Queue (i.e. the 
    //number of elements currently in the Queue)
    public int size() {
	//To be implemented
        return size;
    }

    //return but do not remove the front item in the Queue
    //throw an exception if the Queue is empty
    public Item peek() throws EmptyQueueException {
	//To be implemented
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return queue[front];
    } 
    
    //return the underlying array
    public Item[] getArray() {
	return queue;
    }
}