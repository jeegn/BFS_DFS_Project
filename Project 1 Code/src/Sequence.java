
public class Sequence {
    private Grid grid;
    private Stack<Loc> path;//stores the sequence path
    private String sequence;
    private boolean status;
    //constructor: create a new Sequence for the 
    //specified grid
    public Sequence(Grid grid) {
	this.grid = grid;
	this.path = new Stack<Loc>();
	this.sequence = "";
	this.status = false;
    }

    //resets the grid and the path
    public void reset(Grid grid) {
	this.grid = grid;
	this.path = new Stack<Loc>();
    }

    //(i, j)  is the starting location
    //val is the value that should end the sequence
    public void getSeq(int i, int j, int val) throws EmptyStackException {
	    //To be implemented
        if (this.grid.getLoc(i, j) == null)
            return;

        int v = this.grid.getLoc(i, j).val;
        while (!status) {
            this.path.push(this.grid.getLoc(i, j));
            if (v == val) {
                status = true;
                break;
            }
            if ((this.grid.getLoc((i - 1), j) != null) &&
                    ((this.grid.getLoc((i - 1), j).val) == (v + 1))) {
                getSeq((i - 1), j, val);
            }
            if ((this.grid.getLoc(i, (j + 1)) != null) &&
                    ((this.grid.getLoc(i, (j + 1)).val) == (v + 1))) {
                if (status)
                    break;
                getSeq(i, (j + 1), val);
            }
            if ((this.grid.getLoc((i + 1), j) != null) &&
                    ((this.grid.getLoc((i + 1), j).val) == (v + 1))) {
                if (status)
                    break;
                getSeq((i + 1), j, val);
            }
            if ((this.grid.getLoc(i, (j - 1)) != null) &&
                    ((this.grid.getLoc(i, (j - 1)).val) == (v + 1))) {
                if (status)
                    break;
                getSeq(i, (j - 1), val);
            }
            if (!status)
            this.path.pop();
            break;
        }

    }

    //return a String representation of the sequence
    //starting at the starting location and listing
    //all locations in the sequence in order
    public String toString() {
        Stack<Loc> reversePath = new Stack<Loc>();
        while (!this.path.isEmpty()) {
            try {
                reversePath.push(this.path.pop());
            } catch (EmptyStackException e) {
                e.printStackTrace();
            }
        }
        while (!reversePath.isEmpty()) {
            try {
                sequence += reversePath.pop().toString();
            } catch (EmptyStackException e) {
                e.printStackTrace();
            }
        }
        return sequence;
	    //To be implemented
    }
}