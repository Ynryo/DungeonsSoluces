package sae.solver;

import java.util.HashMap;
import java.util.Map;

import sae.graph.Node;

public class SolverWithDFS extends SolverGeneric {

    private Map<Node, Node> predecesseurs = new HashMap<>();
    
    public SolverWithDFS(Node startingNode, Node endingNode) {
        super(startingNode, endingNode);
        predecesseurs.clear();
        incSteps();
    }

    public void prof(Node node) {
        node.setMarked(true);
        for (Node neighbor : node.neighbors()) {
            if (!neighbor.isMarked()) {
                predecesseurs.put(neighbor, node);
                prof(neighbor);
            }
        }
    }

    @Override
    protected void resolve() {

        for (Node node : getStartingNode().getParent().getNodes()) {
            if (!node.isMarked()) {
                predecesseurs.put(node, null);
                prof(node);
            }
        }
    }
}
