package hw3;

public class TapeDrive<T> {
	
	private T[] tape;
	private int currentPos = 0;
	
	@SuppressWarnings("unchecked")
	public TapeDrive(int capacity) {
		tape = (T[]) new Object[capacity];
	}
	
	// Write in a warp-around manner
	public void write(T t) {
		tape[currentPos] = t;
		currentPos = (currentPos + 1);
	}
	
	public T read() {
		T t = tape[currentPos];
		currentPos = (currentPos + 1) % tape.length;
		return t;
	}
	
	public void reset() {
		currentPos = 0;
	}

}
