package hw2;

public class Polynomial {
	
	/*
	 Runtime: O(N)
	 Memory: O(N)
	 */
	public static Polynomial add(Polynomial p1, Polynomial p2) {
	// TODO: Implement me
		Polynomial result = new Polynomial(" ");
		
		p1.current = p1.polyNode.next;
		p2.current = p2.polyNode.next;
		
		while(p1.current != null && p2.current != null) {
			if (p1.current.getExp() == p2.current.getExp()) {
				
				result.insert(new PolyNode(p1.current.getCoeff()+p2.current.getCoeff(),p1.current.getExp()));
				
				p1.current = p1.current.next;
				p2.current = p2.current.next;
			}
			
			else if (p1.current.getExp() > p2.current.getExp()) {
				result.insert(p1.current);
				p1.current = p1.current.next;
				
			}
			
			else {
				result.insert(p2.current);
				p2.current = p2.current.next;
			}
		}
		
		while(p1.current != null) {
			result.insert(p1.current);
			p1.current = p1.current.next;
		}
		
		while(p2.current != null) {
			result.insert(p2.current);
			p2.current = p2.current.next;
		}
		
		return result;
		
		
	}
	
	
	/*
	 Runtime: O(N^4)
	 Memory: O(N^2)
	 */
	public static Polynomial multiply(Polynomial p1, Polynomial p2) {
	// TODO: Implement me
		Polynomial result = new Polynomial(" ");
		p1.current = p1.polyNode.next;
		p2.current = p2.polyNode.next;
		
		while(p1.current != null) {
			
			while(p2.current != null) {
				PolyNode p = new PolyNode(p1.current.getCoeff()*p2.current.getCoeff(),p1.current.getExp()+p2.current.getExp());
				result.insert(p);
				p2.current = p2.current.next;
			}
			p1.current = p1.current.next;
			p2.current = p2.polyNode.next;
		}
		
		result.current = result.polyNode.next;
		PolyNode tempPre = result.polyNode.next;
		PolyNode temp = result.current.next;
		
		while(result.current.next != null) {
			
			while(temp != null) {
				if(temp.getExp() == result.current.getExp()) {
					result.current.setCoeff(result.current.getCoeff()+temp.getCoeff());
					tempPre.next = temp.next;
					temp = temp.next;
				}
				else {
					tempPre = tempPre.next;
					temp = temp.next;
				}
			}
			
			result.current = result.current.next;
			tempPre = result.current;
			temp = result.current.next;
		}
		
		return result;
		
	}
	
	
	
	private static class PolyNode {
		double coeff;
		int exp;
		private PolyNode next;
		
		public PolyNode(double c, int e, PolyNode n) {
			coeff = c; 
			exp = e; 
			next = n;
		}
		
		public PolyNode() {
			this (0,0,null);
		}
		
		public PolyNode(double c, int e) {
			coeff = c; 
			exp = e; 
			next = null;
		}
		
		public double getCoeff() {
			return coeff;
		}
		
		public int getExp() {
			return exp;
		}
		
		public void setCoeff(double c) {
			coeff = c;
		}
		
	}
	
	private PolyNode polyNode;
	private PolyNode current;
	
	/*
	 Runtime: O(N)
	 Memory: O(N)
	 */
	public Polynomial(String str) {
		// TODO: Implement me
		polyNode = new PolyNode();
		current = polyNode;
		String[] ss = str.split(" ");
		for (int i=0; i<ss.length; i+=2) {
			insert(new PolyNode(Double.parseDouble(ss[i]),Integer.parseInt(ss[i+1])));
		}
		
	}
	
	public void insert(PolyNode p) {
		current.next = p;
		current = p;
	}
	
	
	/*
	 Runtime: O(N)
	 Memory: O(1)
	 */
	public void print() {
		// TODO: Implement me
		PolyNode f = polyNode.next;
		
		while (f.next != null) {
			if(f.getExp() == 1){
				System.out.print(f.getCoeff()+"x"+"+");
			}
			else if (f.getExp() == 0) {
				System.out.print(f.getCoeff()+"+");
			}
			else {
				System.out.print(f.getCoeff()+"x^"+f.getExp()+"+");
			}
			
			f = f.next;
		}
		
		while (f.next == null) {
			if(f.getExp() == 1){
				System.out.print(f.getCoeff()+"x");
			}
			else if (f.getExp() == 0) {
				System.out.print(f.getCoeff());
			}
			else {
				System.out.print(f.getCoeff()+"x^"+f.getExp());
			}
			
			break;
		}
		
		System.out.println("");
	}
}