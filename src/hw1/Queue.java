package hw1;

public class Queue<T> {
	private T[] queue;
	private int front;
	private int rear;

	@SuppressWarnings("unchecked")
	public Queue(int capacity){
		queue = (T[])new Object[capacity + 1];
		front = 0;
		rear = 0;
	}
	
	public boolean isEmpty() {
		return front == rear;
	}
	
	public boolean isFull() {
		return front == (rear+1)%queue.length;
	}
	
	public T peek() {
		return queue[front];
	}
	
	public T dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("The queue is empty and can't dequeue.");
		}
		else {
		T frontValue = queue[front];
		front = (front + 1) % queue.length;
		return frontValue;
		}
	}
	
	public void enqueue(T element) {
		if (isFull()) {
			throw new RuntimeException("The queue is full and can't enqueue.");
		}
		else {
			queue[rear] = element;
			rear = (rear + 1) % queue.length;
		}
			
	}

}
