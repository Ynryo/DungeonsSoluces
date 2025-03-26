package sae.graph;

import sae.graph.Node;

public class Graph {

    private Node nodes;

    public Graph() {
      // TODO document why this constructor is empty
    }

    public void addNote(Node node) {
        
    }

    public void addEdge(Node node, Node node2) {
        node.addNeighbors(node2);
        node2.addNeighbors(node2);
    }

    public String toString() {
        return "Y'a rien ici chef.";
    }
}
