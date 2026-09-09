class myQueue {
    int currSize;
    int start, end;
    int n, q[];
    // Constructor
    public myQueue(int n) {
        this.n = n;
        this.q = new int[n];
        this.start = -1;
        this.end = -1;
        this.currSize = 0;
    }

    public boolean isEmpty() {
        return currSize == 0;
    }

    public boolean isFull() {
        return currSize == n;
    }

    public void enqueue(int x) {
        if(isFull()) return;
        
        if(currSize == 0){
            start = 0;
            end = 0;
        } else{
            end ++;
        }
        
        q[end] = x;
        currSize ++;
        
    }

    public void dequeue() {
        if(isEmpty()) return;
        
        start++;
        currSize--;
        
        if(currSize == 0){
            start = -1;
            end = -1;
        }
    }

    public int getFront() {
        if(isEmpty()) return -1;
        
        return q[start];
    }

    public int getRear() {
        if(isEmpty()) return -1;

        return q[end];
    }
}
