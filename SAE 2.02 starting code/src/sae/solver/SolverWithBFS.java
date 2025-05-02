package sae.solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import sae.graph.GraphSoluce;
import sae.graph.Node;

public class SolverWithBFS extends SolverGeneric {
    private HashMap<Node, Node> dicoNode;
    private List<Node> markedNodes;
    private LinkedList<Node> file;

    public SolverWithBFS(Node startingNode, Node endingNode) {
        super(startingNode, endingNode);
        dicoNode = new HashMap<Node, Node>();
        markedNodes = new ArrayList<Node>();
        file = new LinkedList<Node>();
    }

    @Override
    protected void resolve() {
        Node startingNode = getStartingNode();
        Node endingNode = getEndingNode();

        markedNodes.add(startingNode);
        file.add(startingNode);

        boolean found = false;

        while(!file.isEmpty() && !found) {
            Node s = file.poll();  // <-- enlève le premier élément de la file

            for(Node v: s.neighbors()) {
                if (!markedNodes.contains(v)) {
                    dicoNode.put(v, s);
                    markedNodes.add(v);
                    file.add(v);
                    if (v.equals(endingNode)) {
                        found = true;
                        break;
                    }
                }
            }
        }

        // Reconstitution du chemin
        Node current = endingNode;
		GraphSoluce graphSoluce = getGraphSoluce();
        while (current != null && dicoNode.containsKey(current)) {
            graphSoluce.add(current); // ajoute au début
            current = dicoNode.get(current);
            incSteps();
        }
        if (current == startingNode) {
            graphSoluce.add(startingNode);
        }

    }

    @Override
    public String toString() {
        return "BFS";
    }
}
