public class ClosestPair {
    private Grid grid;
    private Queue<Loc> queue;

    //constructor: create a new ClosestPair object for grid
    public ClosestPair(Grid grid) {
	this.grid = grid;
	this.queue = new Queue<Loc>();
    }

    //search for the closest location with a value 
    //that is equal to the value at start
    //return null if no match is found
    public Loc search(int x, int y) {
	//To be implemented
        Loc start = this.grid.getLoc(x, y);
        if (start == null)
            return null;
        int N = this.grid.size();
        boolean[][] checked = new boolean[N][N];
        boolean[][] inQ = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                checked[i][j] = false;
                inQ[i][j] = false;
            }
        }
        int v = start.val;
        queue.enqueue(start);
        checked[x][y] = inQ[x][y] = true;
        while (!this.queue.isEmpty()) {
            Loc curr = null;
            try {
                curr = this.queue.dequeue();
            } catch (EmptyQueueException e) {
                e.printStackTrace();
            }
            int i = curr.row;
            int j = curr.col;
            inQ[i][j] = false;
            checked[i][j] = true;
            if ((this.grid.getLoc((i - 1), j) != null)) {
                if (((this.grid.getLoc((i - 1), j).val) == v) && (!checked[i -  1][j])) {
                    return this.grid.getLoc((i - 1), j);
                }
                else if (!checked[i - 1][j] && !inQ[i - 1][j]) {
                    queue.enqueue(this.grid.getLoc((i - 1), j));
                    checked[i - 1][j] = inQ[i - 1][j] = true;
                }
            }
            if ((this.grid.getLoc(i, (j + 1)) != null)) {
                if (((this.grid.getLoc(i, (j + 1)).val) == v) && (!checked[i][j + 1])) {
                    return this.grid.getLoc(i, (j + 1));
                }
                else if (!checked[i][j + 1] && !inQ[i][j + 1]) {
                    queue.enqueue(this.grid.getLoc(i, (j + 1)));
                    checked[i][j + 1] = inQ[i][j + 1] = true;
                }
            }
            if ((this.grid.getLoc((i + 1), j) != null)) {
                if (((this.grid.getLoc((i + 1), j).val) == v) && (!checked[i +1][j])) {
                    return this.grid.getLoc((i + 1), j);
                }
                else if (!checked[i + 1][j] && !inQ[i + 1][j]) {
                    queue.enqueue(this.grid.getLoc((i + 1), j));
                    checked[i + 1][j] = inQ[i + 1][j] = true;
                }
            }
            if ((this.grid.getLoc(i, (j - 1)) != null)) {
                if (((this.grid.getLoc(i, (j - 1)).val) == v) && (!checked[i][j - 1])) {
                    return this.grid.getLoc(i, (j - 1));
                }
                else if (!checked[i][j - 1] && !inQ[i][j - 1]) {
                    queue.enqueue(this.grid.getLoc(i, (j - 1)));
                    checked[i][j - 1] = inQ[i][j - 1] = true;
                }
            }
        }
        return null;
    }
}
	