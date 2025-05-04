package sae.graph;

import java.util.ArrayList;
import java.util.List;

import sae.solver.SolverGeneric;

public class GraphSoluce {
    private List<Node> soluce;
    private SolverGeneric graphSoluce;

    public GraphSoluce() {
        this.soluce = new ArrayList<Node>();
    }

    public void add(Node node2add) {
        soluce.add(0, node2add); // ajoute le node au debut de la list
    }

    public List<Node> getSoluce() {
        return soluce;
    }

    @Override
    public String toString() {
        return "GraphSoluce [soluce=" + soluce + ", graphSoluce=" + graphSoluce + "]";
    }
}
