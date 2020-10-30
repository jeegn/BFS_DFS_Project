public class Stack<Item> {
    private int n;
    private Node first;


    //constructor: creates an empty Stack
    public Stack() {
	//To be implemented
        this.first = new Node();
        this.n = 0;
    }

    //adds item to the top of the Stack
    public void push(Item item) {
	//To be implemented
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        if (this.size() == 0) {
            this.first = newNode;
        }
        else {
            newNode.next = this.first;
            this.first = newNode;
        }
        this.n++;
    }

    //removes and returns the top item from the Stack
    //throw EmptyStackException if the Stack is empty
    public Item pop() throws EmptyStackException {
	//To be implemented
        if (this.size() == 0) {
            throw new EmptyStackException();
        }
        Item data = this.first.item;
        this.first = this.first.next;
        this.n--;
        return data;
    }
    
    //return true if the Stack is empty, false else
    public boolean isEmpty() {
	//To be implemented
        return (size() == 0);
    }

    //return the size (number of items) of the Stack
    public int size() {
        return this.n;
	//To be implemented
    }

    //return but do not remove the top item from the Stack
    //throw EmptyStackException if the Stack is empty
    public Item peek() throws EmptyStackException {
	//To be implemented
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return this.first.item;
    }

    private class Node {
	Item item;
	Node next;
    }
}