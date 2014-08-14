package hw1;

public class Stack<T> {
	public static class linkNode<T> {
		private T data;
		private linkNode<T> next;
		
		//constructor for crating the first node
		public linkNode(T data) {
			this.data = data;
			this.next = null;
		}
		
		//constructor for crating the next node
		public linkNode(T data, linkNode<T> next) {
			this.data = data;
			this.next = next;
		}
		
		public T getData() {
			return data;
		}
		
		public void setData(T data) {
			this.data = data;
		}
		
		public linkNode<T> getNext() {
			return next;
		}
		
		public void setNext(linkNode<T> next) {
			this.next = next;
		}
	}
	
	private linkNode<T> top;
	private int currentSize;
	private int size;
	
	public Stack(int capacity) {
		top = null;
		currentSize = 0;
		size = capacity;
	}
	
	public boolean isEmpty() {
		if (currentSize == 0)
			return true;
		else
			return false;
	}
	
	public boolean isFull() {
		if (currentSize == size)
			return true;
		else
			return false;
	}
	
	public T peek() {
		if (isEmpty()) {
			throw new RuntimeException("the stack is empty");
		}
		return top.getData();
	}
	
	public T pop() {
		if (isEmpty()) {
			throw new RuntimeException("the stack is empty");
		}
	    T topValue = top.getData();
		top = top.getNext();
		currentSize --;
		return topValue;
		
	}
	
	public void push(T element) {
		if (isFull()) {
			throw new RuntimeException("the stack is full");
		}
		linkNode<T> newNode = new linkNode<T>(element);
		if (isEmpty()) {
			top = newNode;
		}
		else {
			newNode.setNext(top);
			top = newNode;
		}
		currentSize += 1;
	}

}
