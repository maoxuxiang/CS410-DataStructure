package hw2;

public class test {
	public static void main(String[] args) {
		Polynomial p1 = new Polynomial("5 3 3 1 2 0 ");
		Polynomial p2 = new Polynomial("6 3 2 1");
		p1.print();
		p2.print();
	
		Polynomial.add(p1, p2).print();
		
		Polynomial p3 = Polynomial.multiply(p1, p2);
		p3.print();
	}

}
