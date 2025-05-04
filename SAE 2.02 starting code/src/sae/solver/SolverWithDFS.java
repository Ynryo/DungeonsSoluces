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
		Node endingNode = getEndingNode();
		Node startingNode = getStartingNode();
		dicoNode.put(startingNode, null);
		Prof(startingNode);

		Node current = endingNode;
		int steps = 0;
		while (current != null && dicoNode.containsKey(current)) {
			graphSoluce.add(current);
			current = dicoNode.get(current);
			steps++;
		}
		if (steps > 0)
			steps--; // nn enlève la step en trop parce qu'elle est reloue
		for (int i = 0; i < steps; i++)
			incSteps();

	}

	private void Prof(Node i) {
		markedNodes.add(i);
		for (Node v : i.neighbors()) {
			if (!markedNodes.contains(v)) {
				dicoNode.put(v, i);
				Prof(v);
			}
		}
	}

	@Override
	public String toString() {
		return "DFS";
	}
}
