package sae.solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
		GraphSoluce graphSoluce = getGraphSoluce();
		Node startingNode = getStartingNode();
		
		markedNodes.add(startingNode);
		file.add(startingNode);
		while(!file.isEmpty()) {
			Node s = file.getFirst(); 
			for(Node v: s.neighbors()) {
				if (!markedNodes.contains(v)) {
					dicoNode.put(v, s);
					markedNodes.add(v);
					file.add(v);
				}
			}
		}
		
		Node predecessor = dicoNode.get(getEndingNode());
		while(dicoNode.get(predecessor) != null) {
			graphSoluce.add(predecessor);
			incSteps();
			predecessor = dicoNode.get(predecessor);
		}
	}
}
