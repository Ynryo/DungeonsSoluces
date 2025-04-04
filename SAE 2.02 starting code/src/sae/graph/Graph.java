package sae.graph;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Graph {

    private Set<Node> nodes;
    private String name;
    private int r = new Random().nextInt(100000-10000) + 10000;

    public Graph() {
        super();
        nodes = new HashSet<>();
        this.name = String.valueOf(r);
    }

    public Graph(String name) {
        super();
        nodes = new HashSet<>();
        this.name = name;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public Object getName() {
        return name;
    }

    public void addEdge(Node node, Node node2) {
        node.addNeighbors(node2);
        node2.addNeighbors(node);
    }
    
	@Override
	public String toString() {
		return "Graph [nodes=" + nodes + "]";
	}
}
