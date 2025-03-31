package sae.graph;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<Node> nodes;

    public Graph() {
        super();
        nodes = new HashSet<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void addEdge(Node node, Node node2) {
        node.addNeighbors(node2);
        node2.addNeighbors(node);
    }

    public String toString() {
        return "Y'a rien ici chef, c'est juste un graphe.";
    }
}
