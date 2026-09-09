class myStack {
    
    int top;
    int[] st;
    int n;
    
    public myStack(int n) {
        this.st = new int[n];
        this.n = n;
        this.top = -1;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == n-1);
    }

    public void push(int x) {
        if(isFull()) return;
        
        top = top + 1;
        st[top] = x;
    }

    public void pop() {
        if(isEmpty()) return;
        top = top -1; 
    }

    public int peek() {
        if(isEmpty()) return -1;
        return st[top];
    }
}
