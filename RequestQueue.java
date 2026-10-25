public class RequestQueue {
    private final char[] data;
    private int front = 0, rear = -1, size = 0;
    public RequestQueue(int capacity) { data = new char[capacity]; }
    public void enqueue(char c) {
        if (size == data.length) throw new IllegalStateException("Queue is full");
        rear = (rear + 1) % data.length; data[rear] = c; size++;
    }
    public char dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        char c = data[front]; front = (front + 1) % data.length; size--; return c;
    }
    public boolean isEmpty() { return size == 0; }
}
