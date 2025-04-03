package sae.solver;

import java.util.ArrayList;
import java.util.List;

import sae.graph.Node;

public class GraphSoluce {
    private List<Node> soluce;
    private SolverGeneric graphSoluce;

    public GraphSoluce() {
        this.soluce = new ArrayList<Node>();
    }

    public void add(Node node2add) {
        soluce.add(node2add);
    }

    public List<Node> getSoluce() {
        return soluce;
    }
}
