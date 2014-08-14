package hw1;

public class VipQueue<T> {
	
	private Stack<T> frontStack;
	private Queue<T> backQueue;
	
	public VipQueue(int capacity) {
		frontStack = new Stack<T>(capacity/2);
		backQueue = new Queue<T>(capacity/2);
	}
	
	public boolean isEmpty() {
		if (frontStack.isEmpty() && backQueue.isEmpty())
			return true;
		else
			return false;
	}
	
	public boolean isFull() {
		if (frontStack.isFull() && backQueue.isFull())
			return true;
		else
			return false;
	}
	
	public T peek() {
		if (isEmpty()) {
			throw new RuntimeException("the VipQueue is empty");
		}
		else {
			return frontStack.peek();
		}
	}
	
	public T dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("the VipQueue is empty");
		}
		else {
			while (!frontStack.isEmpty()) {
				return frontStack.pop();
			}
			return backQueue.dequeue();
		}
	}
	
	public void enqueue(T element) {
		if (isFull()) {
			throw new RuntimeException("The VipQueue is full and can't enqueue.");
		}
		else {
			backQueue.enqueue(element);
		}
	}
	
	public void vipEnqueue(T element) {
		if (isFull()) {
			throw new RuntimeException("The VipQueue is full and can't enqueue.");
		}
		else {
			frontStack.push(element);
		}
	}

}
