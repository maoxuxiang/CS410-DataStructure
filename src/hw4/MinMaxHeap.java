package hw4;

public class MinMaxHeap<AnyType extends Comparable<? super AnyType>> {
	
	private int currentSize;
	private AnyType[] arr;
	
	public MinMaxHeap(int capacity){//Constructor
		arr = (AnyType[]) new Comparable[capacity];
		currentSize = 0;
	}
	
	public boolean isFull(){return currentSize == arr.length;}
	public boolean isEmpty(){return currentSize == 0;}
	// COMPLETE THE FOLLOWING METHODS
	
	public void insert(AnyType x){
	//	int hole = ++currentSize;
		int hole = currentSize++;
		arr[hole] = x;
		BubbleUp(hole);
	}//PRE: The heap is not full

	
	private void BubbleUp(int i) {
		double h = log(i + 1,2);
		int height = (int)h;
		
		if(height % 2 == 0) {
			if(i > 0 && arr[i].compareTo(arr[(i-1)/2]) > 0 ) {
				swap(i,(i-1)/2);
				BubbleUpMax((i-1)/2);
			}
			else
				BubbleUpMin(i);
			return;
		}
		
		else
			if(i > 0 && arr[i].compareTo(arr[(i-1)/2]) < 0) {
				swap(i,(i-1)/2);
				BubbleUpMin((i-1)/2);
			}
			else
				BubbleUpMax(i);
			return;
		

	}
	
	private void BubbleUpMin(int i) {
		if(i > 2) {
			if (arr[i].compareTo(arr[(i-3)/4]) < 0) {
				swap(i,(i-3)/4);
				BubbleUpMin((i-3)/4);
			}
			else
				return;
		}
		return;
	}
	
	private void BubbleUpMax(int i) {
		if(i > 2) {
			if (arr[i].compareTo(arr[(i-3)/4]) > 0) {
				swap(i,(i-3)/4);
				BubbleUpMax((i-3)/4);
			}
			else
				return;
		}
		return;
	}
	
	public AnyType min(){
		return arr[0];
	}//PRE: The heap is not empty
	
	public AnyType max(){
		if(currentSize == 1)
			return arr[0];
		else if(currentSize == 2)
			return arr[1];
		else
			return arr[1].compareTo(arr[2])>0 ? arr[1] : arr[2];
			
	}//PRE: The heap is not empty
	
	public int getMaxIndex() {
		if(currentSize == 1)
			return 0;
		else if(currentSize == 2)
			return 1;
		else
			return arr[1].compareTo(arr[2])>0 ? 1 : 2;
	}
	
	public AnyType deleteMin(){
		AnyType minItem = min();
		arr[0] = arr[currentSize];
		currentSize--;
		TrickleDown(0);
		return minItem;
	}//PRE: The heap is not empty
	
	public AnyType deleteMax(){
		AnyType maxItem = max();
		arr[getMaxIndex()] = arr[currentSize];
		currentSize--;
		TrickleDown(getMaxIndex());
		return maxItem;
	}
	
	public void TrickleDown(int i) {
		double h = log(i + 1,2);
		int height = (int)h;
		
		if(height % 2 == 0)
			TrickleDownMin(i);
		else
			TrickleDownMax(i);
	}
	
	public void TrickleDownMin(int i) {
		int m;
		if(currentSize >= 2*i+2) {
			m = MinChildIndex(i);
			if(m > 4*i+2) {
				if(arr[m].compareTo(arr[i]) < 0) {
					swap(m,i);
					if(arr[m].compareTo(arr[(m-1)/2]) > 0) 
						swap(m,(m-1)/2);
					TrickleDownMin(m);
				}
				return;
			}
			if(m < 2*i + 3) {
				if(arr[m].compareTo(arr[1]) < 0)
					swap(m,i);
				return;
			}
		}
		else 
			return;
			
	}
	
	public void TrickleDownMax(int i) {
		int m;
		if(currentSize >= 2*i+2) {
			m = MaxChildIndex(i);
			if(m > 4*i+2) {
				if(arr[m].compareTo(arr[i]) > 0) {
					swap(m,i);
					if(arr[m].compareTo(arr[(m-1)/2]) < 0) 
						swap(m,(m-1)/2);
					TrickleDownMax(m);
				}
				return;
			}
			if(m < 2*i + 3) {
				if(arr[m].compareTo(arr[1]) > 0)
					swap(m,i);
				return;
			}
		}
		else 
			return;
			
	}

	public void swap(int index1, int index2) {
		AnyType temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	public int MinChildIndex(int i) {
		double h = log(i + 1,2);
		int height = (int)h;
		int m = 0;
		
		if(height % 2 == 0) {
			
			if(currentSize >= 4*i + 7)
				m = findMin(findMin(4*i+3,4*i+4),findMin(4*i+5,4*i+6));
			if(currentSize == 4*i + 6)
				m = findMin(findMin(4*i+3,4*i+4),4*i+5);
			if(currentSize == 4*i + 5)
				m = findMin(4*i+3,4*i+4);
			if(currentSize == 4*i + 4)
				m = 4*i+3;
			if(currentSize < 4*i + 4 && currentSize >= 2*i + 3)
				m = findMin(2*i+1,2*i+2);
			if(currentSize == 2*i + 2)
				m = 2*i + 2;

		} 
		
		else {
			if(currentSize >= 2*i + 3)
				m = findMin(2*i+1,2*i+2);
			if(currentSize == 2*i + 2)
				m = 2*i+1;
		}

		return m;
	}
	
	public int MaxChildIndex(int i) {
		double h = log(i + 1,2);
		int height = (int)h;
		int m = 0;
		
		if(height % 2 == 1) {
			
			if(currentSize >= 4*i + 7)
				m = findMax(findMax(4*i+3,4*i+4),findMax(4*i+5,4*i+6));
			if(currentSize == 4*i + 6)
				m = findMax(findMax(4*i+3,4*i+4),4*i+5);
			if(currentSize == 4*i + 5)
				m = findMax(4*i+3,4*i+4);
			if(currentSize == 4*i + 4)
				m = 4*i+3;
			if(currentSize < 4*i + 4 && currentSize >= 2*i + 3)
				m = findMax(2*i+1,2*i+2);
			if(currentSize == 2*i + 2)
				m = 2*i + 2;

		} 
		
		else {
			if(currentSize >= 2*i + 3)
				m = findMax(2*i+1,2*i+2);
			if(currentSize == 2*i + 2)
				m = 2*i+1;
		}

		return m;
	}
	
	public int findMin(int a, int b) {
		if(arr[a].compareTo(arr[b]) > 0)
			return b;
		else
			return a;
	}
	
	public int findMax(int a, int b) {
		if(arr[a].compareTo(arr[b]) > 0)
			return a;
		else
			return b;
	}
	
	static public double log(double value, double base) {

		return Math.log(value) / Math.log(base);
		    
	}
	
	public void output() {
		for(int i =0; i<arr.length; i++) {
			System.out.println("arr"+"["+i+"]"+arr[i].toString());
		}
	
	}
	
	//PRE: The heap is not empty
	//Private methods go here.
	
}