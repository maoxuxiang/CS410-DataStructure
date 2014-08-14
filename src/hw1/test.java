package hw1;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Integer> m = new Stack<Integer>(5);
		for (int i=0; i<5; i++){
			m.push(i);
		}
		while (!m.isEmpty())
			System.out.printf("->%d", m.pop());
		
		System.out.println();
		
		Queue<Integer> s = new Queue<Integer>(5);
		for (int i=0; i<5; i++){
			s.enqueue(i);
		}

		while (!s.isEmpty())
			System.out.printf("->%d", s.dequeue());
		
		
        }
	
}
