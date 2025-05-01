package sae.solver;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import sae.dungeon.Coord;
import sae.graph.GraphSoluce;
import sae.graph.Node;

public class SolverWithAstar extends SolverGeneric {
	private LinkedList<Node> closedList;
	private PriorityQueue<Node> openList;
	
    public SolverWithAstar(Node startingNode, Node endingNode) {
        super(startingNode, endingNode);
        closedList = new LinkedList<Node>();
        openList = new PriorityQueue<Node>(comparatorHeuristique);
    }

	@Override
	protected void resolve() {
		GraphSoluce graphSoluce = getGraphSoluce();
		Node startingNode = getStartingNode();
		Node endingNode = getEndingNode();
		Coord endingCoord = endingNode.getCoord();
		
		openList.add(startingNode);
		while(!openList.isEmpty()) {
			Node u = openList.poll();
			 
			Coord coordU = u.getCoord();
			if (coordU.getX() == endingCoord.getX() && coordU.getY() == endingCoord.getY()) {
				// Reconstituer chemin méthode
				break; // Pas propre
			}
			 
			for (Node v: u.neighbors()) {
				if (!closedList.contains(v) && !openList.contains(v)) {
					openList.add(v);
				}
			}
			closedList.add(u);
		}
	}
	
	public Comparator<Node> comparatorHeuristique = new Comparator<Node>() {
		public int compare(Node nodeA, Node nodeB) {
			Coord coordA = nodeA.getCoord();
			Coord coordB = nodeB.getCoord();
			return (coordB.getX() - coordA.getX()) + (coordB.getY() - coordA.getY());
		}
	};
}
