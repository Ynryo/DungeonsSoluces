package sae.solver;

import sae.graph.Node;


public class SolverGeneric implements Solver {
    private Node startingNode;
    private Node endingNode;
    private int steps;

    public SolverGeneric(Node node1, Node node2) {

    }

    public GraphSoluce getGraphSoluce() {
    }

    public int getSteps() {
        return steps;
    }

    public void incSteps() {
        steps++;
    }

    public Node getStartingNode() {
        return startingNode;
    }

    public Node getEndingNode() {
        return endingNode;
    }

    public void solve() {

    }

    protected void resolve() {

    }

    private void initializeResolution() {

    }
}
