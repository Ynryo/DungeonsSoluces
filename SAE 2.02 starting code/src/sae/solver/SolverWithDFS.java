package sae.solver;

<<<<<<< HEAD
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
=======
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
>>>>>>> 8504583745afba01c325bd8cc852f3c83269ddaa
    }

	@Override
	protected void resolve() {
		GraphSoluce gs = getGraphSoluce();
		Node startingNode = getStartingNode();
		dicoNode.put(startingNode, null);
		Prof(startingNode);
		
		Node predecessor = dicoNode.get(getEndingNode());
		while(dicoNode.get(predecessor) != null) {
			gs.add(predecessor);
			predecessor = dicoNode.get(predecessor);
		}
		//System.out.println(gs.getSoluce());
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
