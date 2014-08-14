package hw5;

/**
 * Factory used for creating nodes
 */
public final class NodeFactory {

    public static Node create(String name) {

        // TODO: Create a node with the given name based on your implementation and return it
        // i.e. return new MyNode(name);
    	return new MyNode(name);
    }

}
