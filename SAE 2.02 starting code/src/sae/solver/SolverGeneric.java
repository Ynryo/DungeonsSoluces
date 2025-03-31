package sae.solver;

import sae.graph.Node;


public abstract class SolverGeneric implements Solver {
    private Node startingNode;
    private Node endingNode;
    private int steps;

    SolverGeneric(Node startingNode, Node endingNode) {
        this.startingNode = startingNode;
        this.endingNode = endingNode;
    }

    public GraphSoluce getGraphSoluce() {
            return null;
      // TODO document why this method is empty
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
