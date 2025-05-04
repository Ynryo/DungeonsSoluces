package sae.solver;

import java.util.*;
import sae.dungeon.Coord;
import sae.graph.GraphSoluce;
import sae.graph.Node;

public class SolverWithAstar extends SolverGeneric {
    private List<Node> closedList;
    private PriorityQueue<Node> openList;
    private HashMap<Node, Node> dicoNode;

    public SolverWithAstar(Node startingNode, Node endingNode) {
        super(startingNode, endingNode);
        closedList = new ArrayList<Node>();
        openList = new PriorityQueue<Node>(compareHeuristique);
        dicoNode = new HashMap<Node, Node>();
    }

    @Override
    protected void resolve() {
        openList.add(getStartingNode());
        while (!openList.isEmpty()) {
            Node u = openList.poll();
            Coord coordU = u.getCoord();
            Coord coordObj = getEndingNode().getCoord();
            if (coordU.getX() == coordObj.getX() && coordU.getY() == coordObj.getY()) {
                reconstituerChemin(u);
                break;
            }
            for (Node v : u.neighbors()) {
                if (!closedList.contains(v) && !openList.contains(v)) {
                    v.setCout(u.getCout() + 1);
                    openList.add(v);
                }
            }
            closedList.add(u);
        }
    }

    private void reconstituerChemin(Node u) {
        while (!u.equals(getStartingNode())) {
            if (closedList.contains(u))
                ;
            for (Node v : u.neighbors()) {
                if (v.getCout() == u.getCout() - 1) {
                    dicoNode.put(u, v);
                    u = v;
                }
            }
        }
        GraphSoluce graphSoluce = getGraphSoluce();
        Node predecessor = dicoNode.get(getEndingNode());
        graphSoluce.add(getEndingNode());
        while (dicoNode.get(predecessor) != null) {
            graphSoluce.add(predecessor);
            incSteps();
            predecessor = dicoNode.get(predecessor);
        }
        graphSoluce.add(getStartingNode());
        // Pour éviter une erreur où la solution disparaît car les openList & closedList
        // ont déjà la solution
        closedList = new ArrayList<Node>();
        openList = new PriorityQueue<Node>(compareHeuristique);
        dicoNode = new HashMap<Node, Node>();
    }

    public Comparator<Node> compareHeuristique = new Comparator<Node>() {
        @Override
        public int compare(Node node1, Node node2) {
            Coord node1Coord = node1.getCoord();
            Coord node2Coord = node2.getCoord();
            return (node2Coord.getY() - node1Coord.getY()) + (node2Coord.getX() - node1Coord.getX());
        }
    };

    @Override
    public String toString() {
        return "A*";
    }
}
