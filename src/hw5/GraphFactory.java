package hw5;

/**
 * Factory used for creating graphs
 */
public final class GraphFactory {

    public static Graph create(int nodeCount) {

        // TODO: Create a graph based on your implementation and return it
        // You may use (you don't have to) the number of nodes that will be added to the graph for the constructor
        // i.e. return new MyGraph();
        // i.e. return new YourGraph(nodeCount);
        return new MyGraph(nodeCount);
    }

}
