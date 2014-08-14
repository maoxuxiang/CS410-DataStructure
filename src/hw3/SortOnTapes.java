package hw3;

import java.util.Random;

public class SortOnTapes {
	// Constants. These will be changed when testing your program.
	public static int TapeSize = 100;
	public static int MainMemorySize = 10;
	public static int cutoff = 5;
	
	//Memory to be used when sorting
	private static Integer[] memory = new Integer[MainMemorySize];
	
	/**
	 * this creates a new tape drive if size n and fills it with random integers.
	 * @param n The size or capacity of the new drive
	 * @return the new TapeDrive containing n random integers
	 */
	public static TapeDrive<Integer> generateRandomTape(int n) {
		// TODO: write this
		Random r1 = new Random();
		TapeDrive<Integer> t1 = new TapeDrive<Integer>(n);
		
		//write random number into tape
		for (int i=0; i<n; i++) {
			t1.write(r1.nextInt());
		}
		t1.reset();
		return t1;
		
		
	} 
	
	
	/**
	 * This sorts a tape drive using an external sorting algorithm. the sorted data
	 * should be placed on t1.
	 * @param t1 the drive to sort
	 */
	public static void sort(TapeDrive<Integer> t1) {
		//Additional tapes to use when sorting.
		TapeDrive<Integer> t2 = new TapeDrive<Integer>(TapeSize);
		TapeDrive<Integer> t3 = new TapeDrive<Integer>(TapeSize);
		TapeDrive<Integer> t4 = new TapeDrive<Integer>(TapeSize);
		
		//TODO: Write this
		int chunkSize = MainMemorySize;
		int numChunk;
		int leftover;
		int pass = (int)log(TapeSize/MainMemorySize, 2) + 1;
	
		initialChunk(t1, t3, t4);
		t1.reset();
		t3.reset();
		t4.reset();
		
		for(int i=0; i<pass - 1; i++) {
			numChunk = TapeSize/(2*chunkSize);
			leftover = TapeSize - 2*chunkSize*numChunk;
			if(i % 2 == 0) {
				for(int j=0; j<numChunk; j++) {
					if(j % 2 == 0) {
						mergeSort(t3, t4, t1, chunkSize);
					}
					else {
						mergeSort(t3, t4, t2, chunkSize);
					}
				}
				if(numChunk % 2 == 0)
					process_leftover(t3, t4, t1, leftover, chunkSize);
				if(numChunk % 2 == 1)
					process_leftover(t3, t4, t2, leftover, chunkSize);
			}
			if (i % 2 == 1) {
				for(int j=0; j<numChunk; j++) {
					if(j % 2 == 0) {
						mergeSort(t1, t2, t3, chunkSize);
					}
					if(j % 2 == 1) {
						mergeSort(t1, t2, t4, chunkSize);
					}
				}
				if(numChunk % 2 == 0)
					process_leftover(t1, t2, t3, leftover, chunkSize);
				if(numChunk % 2 == 1)
					process_leftover(t1, t2, t4, leftover, chunkSize);
			}
			
			chunkSize *= 2; 
			t1.reset();
			t2.reset();
			t3.reset();
			t4.reset();
		}
		
		leftover = TapeSize - chunkSize;
		if(leftover != 0) {
			if(pass % 2 == 0) {
				process_lastpass(t1, t2, t3, chunkSize, leftover);
				t1.reset();
				t2.reset();
				t3.reset();
				
				for(int i=0; i<TapeSize; i++) {
					t1.write(t3.read());
				}
			}
			else
				process_lastpass(t3, t4, t1, chunkSize, leftover);
		}
		else
			if(pass % 2 == 1) {
				for(int i=0; i<TapeSize; i++) {
					t1.write(t3.read());
				}
				
			}
		
		t1.reset();
		t2.reset();
		t3.reset();
		t4.reset();
				
	} 
	
	public static void mergeSort(TapeDrive<Integer> a, TapeDrive<Integer> b, TapeDrive<Integer> c, int num){
		int countA = 0;
		int countB = 0;
		int tempA = a.read();
		int tempB = b.read();
		while(countA < ( num - 1 ) && countB < ( num - 1)){
			if(tempA <= tempB){
				c.write(tempA);
				tempA = a.read(); 
				countA++;
			}
			else{
				c.write(tempB);
				tempB = b.read();
				countB++;
			}
		}
		if(countA == ( num - 1 )){
			while(countB < ( num - 1)){
				if(tempA <= tempB){
					c.write(tempA);
					countA += 1;
					break;
				}
				else{
					c.write(tempB);
					tempB = b.read();
					countB++;
				}
			}
			if(countA == num){
				while(countB < ( num - 1)){
					c.write(tempB);
					tempB = b.read();
					countB++;
				}
				c.write(tempB);
			}
			else{
				if(tempA <= tempB){
					c.write(tempA);
					c.write(tempB);
				}
				else{
					c.write(tempB);
					c.write(tempA);
				}
			}
		}
		else if(countB == ( num - 1 )){
			while(countA < ( num - 1)){
				if(tempB <= tempA){
					c.write(tempB);
					countB +=1;
					break;
				}
				else{
					c.write(tempA);
					tempA = a.read();
					countA++;
				}
			}
			if(countB == num){
				while(countA < ( num - 1)){
					c.write(tempA);
					tempA = a.read();
					countA++;
				}
				c.write(tempA);
			}
			else{
				if(tempA <= tempB){
					c.write(tempA);
					c.write(tempB);
				}
				else{
					c.write(tempB);
					c.write(tempA);
				}
			}
		}
		
		
	}
	
	public static void process_leftover(TapeDrive<Integer> a, TapeDrive<Integer> b, TapeDrive<Integer> c, int leftover, int chunksize){
		if(leftover == 0){
			return;
		}
		if(leftover <= chunksize){
			for(int p = 0; p<leftover; p++){
				c.write(a.read());
			}
		}
		else{
			int count_a = 0;
			int count_b = 0;
			Integer tem_a = a.read();
			Integer tem_b = b.read();
			while(count_a < chunksize && count_b < (leftover - chunksize)){
				if(tem_a <= tem_b){
					if(count_a < chunksize-1){
						c.write(tem_a);
						tem_a = a.read();
						count_a++;
					}
					else{
						c.write(tem_a);
						count_a++;
						break;
					}
				}
				else{
					if(count_b < (leftover - chunksize - 1)){
						c.write(tem_b);
						tem_b = b.read();
						count_b++;
					}
					else{
						c.write(tem_b);
						count_b++;
						break;
					}
				}
			}
			while(count_a < chunksize -1 ){
				c.write(tem_a);
				tem_a = a.read();
				count_a++;
			}
			while(count_b < (leftover - chunksize - 1)){
				c.write(tem_b);
				tem_b = b.read();
				count_b++;
			}
			if(count_a == chunksize - 1){
				c.write(tem_a);
			}
			else{
				c.write(tem_b);
			}
		}
	}
	
	public static void process_lastpass(TapeDrive<Integer> a, TapeDrive<Integer> b, TapeDrive<Integer> c, int chunksize, int leftover){
		if(leftover == 0){
			return;
		}
		int count_a = 0;
		int count_b = 0;
		Integer tem_a = a.read();
		Integer tem_b = b.read();
		while(count_a < chunksize && count_b < leftover){
			if(tem_a <= tem_b) {
				if(count_a < chunksize - 1){
					c.write(tem_a);
					tem_a = a.read();
					count_a++;
				}
				else{
					c.write(tem_a);
					count_a++;
					break;
				}
			}
			else {
				if(count_b < leftover - 1){
					c.write(tem_b);
					tem_b = b.read();
					count_b++;
				}
				else{
					c.write(tem_b);
					count_b++;
					break;
				}
			}		
		}
		while(count_a < chunksize -1 ){
			c.write(tem_a);
			tem_a = a.read();
			count_a++;
		}
		while(count_b < leftover -1 ){
			c.write(tem_b);
			tem_b = b.read();
			count_b++;
		}
		if(count_a == chunksize - 1){
			c.write(tem_a);
		}
		else{
			c.write(tem_b);
		}
	}
	

	
	public static void initialChunk(TapeDrive<Integer> t1, TapeDrive<Integer> t3, TapeDrive<Integer> t4) {
		int numChunk =  TapeSize/MainMemorySize;
		int left = TapeSize - numChunk * MainMemorySize;
		
		for (int i=0; i<numChunk; i++) {
			for (int j=0; j<MainMemorySize; j++) {
				memory[j] = t1.read();
			}
			
			quickSort(memory);
			
			if (i%2 == 0) {
				for(int a=0; a<MainMemorySize; a++) {
					t3.write(memory[a]);
				}
			}
			
			if (i%2 == 1) {
				for(int a=0; a<MainMemorySize; a++) {
					t4.write(memory[a]);
				}
			}
		}
		
		if (left > 0) {
			for(int i=0; i<left; i++) {
				memory[i] = t1.read();
			}
			
			quickSort(memory);
			
			if (numChunk % 2 == 0) {
				for(int i=0; i<left; i++) {
					t3.write(memory[i]);
				}
			}
			
			if (numChunk % 2 == 1) {
				for(int i=0; i<left; i++) {
					t4.write(memory[i]);
				}
			}
		}
	}
	//drive for quickSort
	public static void quickSort(Integer[] a) {
		quickSort(a,0, a.length-1);
	}
	
	private static void quickSort(Integer[] a, int left, int right) {
		if(left + cutoff <= right) {
			int pivot = median3(a, left, right);
			int i =left , j = right - 1;
			
			for(;;) {
				while(a[++i].compareTo(pivot)<0) {}
				while(a[--j].compareTo(pivot)>0) {}
				if(i < j)
					swapReferences(a, i, j);
				else
					break;
			}
			
			swapReferences(a, i, right -1);
			
			quickSort(a, left, i - 1);
			quickSort(a, i + 1, right);
		} 
		else
			insertionSort(a);
	}
	
	private static int median3(Integer[] a, int left, int right) {
		int center = (left + right) / 2;
		if (a[center].compareTo(a[left]) < 0)
			swapReferences(a, left, center);
		if (a[right].compareTo(a[left]) < 0)
			swapReferences(a, left, right);
		if (a[right].compareTo(a[center]) < 0)
			swapReferences(a, center, right);
		
		//place pivot at position right -1
		swapReferences(a, center, right - 1);
		return a[right - 1];	
	}
	
	private static void swapReferences(Integer[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	private static void insertionSort(Integer[] a) {
		int j;
		
		for(int p = 1; p < a.length; p++) {
			int temp = a[p];
			
			for(j = p; j > 0 && temp < a[j-1]; j--)
				a[j] = a[j - 1];
			a[j] = temp;
		}
	}
	
	static public double log(double value, double base) {

		return Math.log(value) / Math.log(base);
		    
	}
	
	public static void main(String[] args) {
		TapeDrive<Integer> drive = generateRandomTape(TapeSize);
		sort(drive);
		
		//This tests to see if the drive is, indeed, sorted.
		boolean sorted = true;
		int prev = Integer.MIN_VALUE;
		for (int i=0; i<TapeSize; i++) {
			int p = prev;
			sorted &= p <= (prev = drive.read());
		}
		
		if (sorted)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	} 
	


}
