package sae.graph;

import java.util.HashSet;
import java.util.Set;

import sae.dungeon.Coord;

public class Node {
    private String name;
    private Set<Node> neighbors;
    private Coord coord;
    private Graph parent;

    public Node(String name, Coord coord, Graph graph) {
        this.name = name;
        this.coord = coord;
        this.neighbors = new HashSet<>();
        this.parent =  graph;
    }

    public Graph getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public Set<Node> neighbors() {
        return neighbors;
    }

    public void addNeighbors(Node node) {
        neighbors.add(node);
    }

    @Override
    public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;

        return this.getCoord() == other.getCoord();
    }

    @Override
    public String toString() {
        return String.format("Node [name=%-2s, coord=%-7s, neighbors=%d, graph=%-5s]", name, coord, neighbors.size(), getParent().getName());
    }
}
