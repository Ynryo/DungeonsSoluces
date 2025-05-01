package sae.solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import sae.graph.GraphSoluce;
import sae.graph.Node;

public class SolverWithDFS extends SolverGeneric {
	private HashMap<Node, Node> dicoNode;
	private List<Node> markedNodes;
    
    public SolverWithDFS(Node startingNode, Node endingNode) {
        super(startingNode, endingNode);
        dicoNode = new HashMap<Node, Node>();
        markedNodes = new ArrayList<Node>();
    }
 
	@Override
	protected void resolve() {
		GraphSoluce graphSoluce = getGraphSoluce();
		Node startingNode = getStartingNode();
		dicoNode.put(startingNode, null);
		Prof(startingNode);
		
		Node predecessor = dicoNode.get(getEndingNode());
		while(dicoNode.get(predecessor) != null) {
			graphSoluce.add(predecessor);
			incSteps();
			predecessor = dicoNode.get(predecessor);
		}
	}
	
	private void Prof(Node i) {
		markedNodes.add(i);
		for(Node v : i.neighbors()) {
			if(!markedNodes.contains(v)) {
				dicoNode.put(v, i);
				Prof(v);
			}
		}
	}
}
