package hw5;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class MyGraph extends Graph {
	
	private MyHashTable<Integer, Node> adjacencyList;
	private MyHashTable<String, Node> vertexInfo;
	private MyHashTable<Integer, Node> vertexInfo2;
	private int[] indegree;
	private int nodeNum = 0;
//	private boolean isAcyclic;
	
	public MyGraph(int size) {
		adjacencyList = new MyHashTable<Integer, Node>(size);
		vertexInfo = new MyHashTable<String, Node>(size);
		vertexInfo2 = new MyHashTable<Integer, Node>(size);
		indegree = new int[size];
	//	isAcyclic = true;
	}
	
	public MyGraph() {
		adjacencyList = new MyHashTable<Integer, Node>();
		vertexInfo = new MyHashTable<String, Node>();
		vertexInfo2 = new MyHashTable<Integer, Node>();
		indegree = new int[2003];
	//	isAcyclic = true;
	}

	@Override
	public void addNode(Node node) {
		
		if (node == null) {
            throw new IllegalArgumentException("null");
        }
		
		adjacencyList.put(node.hashCode(), node);
		vertexInfo.put(node.getName(), node);
		vertexInfo2.put(node.hashCode(), node);
		nodeNum++;

	}

	@Override
	public void addEdge(Node node1, Node node2) {
	//	MyEntry edgelist = adjacencyList.getNextEntry(node1.hashCode());
		MyEntry<Integer, Node> edge = new MyEntry<Integer, Node>(node2.hashCode(), node2);
		
		if(adjacencyList.getNextEntry(node1.hashCode()) == null) {
			adjacencyList.getEntry(node1.hashCode()).setNext(edge);
		}
		else {
			edge.setNext(adjacencyList.getNextEntry(node1.hashCode()));
			adjacencyList.getEntry(node1.hashCode()).setNext(edge);
		}
		
		indegree[node2.hashCode()]++;

	}
	
//	public int indegreeOf(Node n) {
	//	return indegree[n.hashCode()];
//	}

	@Override
	public Node lookupNode(int id) {
		return adjacencyList.get(id);
	}

	@Override
	public Node lookupNode(String name) {
		return vertexInfo.get(name);
	}

	@Override
	public boolean isAcyclic() {
		HashSet<Node> s = new HashSet<Node>();
		int counter = 0;
		ArrayList<Integer> L = new ArrayList<Integer>();
		int[] indegree2 = new int[indegree.length];
		for(int i=0; i<indegree.length; i++) {
			indegree2[i] = indegree[i];
		}
		
		for(int i=0; i<indegree2.length; i++) {
			if(indegree2[i] == 0)
			
				s.add(vertexInfo2.get(i));
		}
		

	
		while(!s.isEmpty())  { 
			try {
				Node n = s.iterator().next();
				s.remove(n);
				L.add(n.getId());
				counter++;
				
				MyEntry<Integer, Node> e = adjacencyList.getNextEntry(n.hashCode());
				
				while(e != null) {
					int i = e.getValue().getId();
					indegree2[i]--;
					e = e.getNext();
					if(indegree2[i] == 0)
						s.add(vertexInfo2.get(i));
				}
			} catch (Exception e) {
				
			}

		}
		if (counter != nodeNum)
			return false;
		else
			return true;

	}

	@Override
	public int[] sort() {

		HashSet<Node> s = new HashSet<Node>();
		int counter = 0;
		ArrayList<Integer> L = new ArrayList<Integer>();
		
		for(int i=0; i<indegree.length; i++) {
			if(indegree[i] == 0)
			
				s.add(vertexInfo2.get(i));
		}
		

	
		while(!s.isEmpty())  { 
			try {
				Node n = s.iterator().next();
				s.remove(n);
				L.add(n.getId());
				counter++;
				
				MyEntry<Integer, Node> e = adjacencyList.getNextEntry(n.hashCode());
				
				while(e != null) {
					int i = e.getValue().getId();
					indegree[i]--;
					e = e.getNext();
					if(indegree[i] == 0)
						s.add(vertexInfo2.get(i));
				}
			} catch (Exception e) {
				
			}

		}
	//	if (counter != nodeNum)
		//	isAcyclic = false;
		
//		System.out.println(L.toString());
		
		int size=L.size();  
        int[] array=new int[size];  
  
		for(int i=0;i<L.size();i++){  
			array[i]=(int)L.get(i);  
        }  
		
	//	for(int i = 0; i<array.length; i++) {
	//		System.out.println(array[i]);
	//	}
	//	System.out.println(counter);
		

		return array;
	}

}
