package sae.solver;

import java.util.*;

import sae.dungeon.Coord;
import sae.graph.GraphSoluce;
import sae.graph.Node;

public class SolverWithAstar extends SolverGeneric {
    private Set<Node> closedList;
    private PriorityQueue<Node> openList;
    private Map<Node, Node> cameFrom; // Pour reconstituer le chemin
    private Map<Node, Integer> gScore; // Coût du départ à ce nœud
    private Map<Node, Integer> fScore; // gScore + heuristique

    public SolverWithAstar(Node startingNode, Node endingNode) {
        super(startingNode, endingNode);
        closedList = new HashSet<Node>();
        cameFrom = new HashMap<Node, Node>();
        gScore = new HashMap<Node, Integer>();
        fScore = new HashMap<Node, Integer>();
        // Le comparator a besoin de fScore, donc on le définit après l'init
        openList = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return Integer.compare(
                    fScore.getOrDefault(a, Integer.MAX_VALUE),
                    fScore.getOrDefault(b, Integer.MAX_VALUE)
                );
            }
        });
    }

    @Override
    protected void resolve() {
        Node start = getStartingNode();
        Node goal = getEndingNode();

        gScore.put(start, 0);
        fScore.put(start, heuristic(start, goal));
        openList.add(start);

        while (!openList.isEmpty()) {
            Node current = openList.poll();

            // if (current.equals(goal)) {
            //     // Chemin trouvé, on stocke la solution
            //     List<Node> path = reconstructPath(cameFrom, current);
            //     setGraphSoluce(new GraphSoluce(path));
            //     return;
            // }

            closedList.add(current);

            for (Node neighbor : current.neighbors()) {
                if (closedList.contains(neighbor))
                    continue;

                int tentativeG = gScore.get(current) + 1; // Coût d'un pas

                if (!gScore.containsKey(neighbor) || tentativeG < gScore.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeG);
                    fScore.put(neighbor, tentativeG + heuristic(neighbor, goal));
                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
        }
        // Si aucun chemin trouvé, on stocke une solution vide
        // setGraphSoluce(new GraphSoluce(new ArrayList<Node>()));
    }

    // Heuristique = distance de Manhattan
    private int heuristic(Node a, Node b) {
        Coord ca = a.getCoord();
        Coord cb = b.getCoord();
        return Math.abs(ca.getX() - cb.getX()) + Math.abs(ca.getY() - cb.getY());
    }

    // Reconstitue le chemin à partir de cameFrom
    private List<Node> reconstructPath(Map<Node, Node> cameFrom, Node current) {
        LinkedList<Node> path = new LinkedList<Node>();
        path.addFirst(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.addFirst(current);
        }
        return path;
    }
}
