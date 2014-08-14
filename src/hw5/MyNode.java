package hw5;

public class MyNode implements Node {
	
	private String name;
	private static int IDD = 0;
	private int ID;
	
	public MyNode(String name, int ID) {
		this.name = name;
		this.ID = IDD;
		MyNode.IDD++;
	}
	
	public MyNode(String name) {
		this(name, IDD);
		
	}

	public String getName() {
		return name;
	}

	public int getId() {

		return ID;
	}
	
	public int hashCode() {
		return ID;
		
	}

}
