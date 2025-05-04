package sae.transform;import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sae.dungeon.Direction;
import sae.dungeon.Dungeon;
import sae.dungeon.DungeonSoluce;
import sae.graph.Graph;
import sae.graph.GraphSoluce;
import sae.graph.Node;
import sae.dungeon.Room;
import java.util.Set;
import java.util.concurrent.ForkJoinWorkerThread;
public class Dungeon2Graph {
    private Dungeon dungeon;
    private HashMap<Room, Node> dicoRoom;
    public Dungeon2Graph(Dungeon nDungeon) {
        // Définition du donjon
        dungeon = nDungeon;
        // Création d'une liste de pièces
        Set<Room> listRoom = dungeon.getRooms();
        // Définition du dictionnaire pièce->noeud
        dicoRoom = new HashMap<>(listRoom.size());
        Graph graph = new Graph();
        for (Room room : dungeon.getRooms()) { // convert room into node without neighbors
            Node value = new Node(room.getName(), room.getCoords(), graph);
            graph.addNode(value);
            dicoRoom.put(room, value);
        }
        // create edges (neighbors)
        for (Room room : dungeon.getRooms()) {
            for (Room roomAdjacents : room.getNextRooms().values()) {
                if (!mappedNode(room).neighbors().contains(mappedNode(roomAdjacents))) {
                    graph.addEdge(mappedNode(room), mappedNode(roomAdjacents));
                    // System.out.println(String.format("Nombre de neighbors : %d",
                    // mappedNode(room).neighbors().size()));
                }
            }
        }
        System.out.println(graph);
    }
    public Node mappedNode(Room room) {
        return dicoRoom.get(room); // get from dico (already transform from rooms to nodes)
    }
    public DungeonSoluce transform(GraphSoluce soluceGraph) {
        DungeonSoluce dungeonSoluce = new DungeonSoluce();
        List<Room> roomList = new ArrayList<Room>();
        // Parcourir les noeuds de la solution graphe pour trouver leurs équivalents en
        // pièce donjon
        for (Node node : soluceGraph.getSoluce()) {
            for (Room room : dicoRoom.keySet()) {
                if (dicoRoom.get(room) == node) {
                    roomList.add(room); // Si graphe soluce était C->B->A, la liste pièce reste C->B->A (A en haut de la
                                        // liste)
                }
            }
        }
        // On utilise un iterator au lieu d'une boucle for pour pouvoir facilement
        // connaître le prochain
        Iterator<Room> it = roomList.iterator();
        // Pour initier la pièce "précédente"
        Room lastRoom = null;
        if (it.hasNext()) {
            lastRoom = it.next();
        }
        // Tant qu'il reste des pièces dans notre liste de pièce
        while (it.hasNext()) {
            Room currentRoom = it.next(); // On initialise la pièce "courante"
            for (Direction dir : Direction.values()) { // On parcours les directions
                Map<Direction, Room> nextRooms = lastRoom.getNextRooms(); // On récupère les pièces voisines de la pièce
                                                                          // courante
                if (nextRooms.get(dir) == currentRoom) { // Si une pièce voisine de la pièce courante EST la pièce
                                                         // précédente
                    dungeonSoluce.addDirection(dir); // On retient la direction
                }
            }
            lastRoom = currentRoom; // Avant de passer à la prochaine pièce, la pièce courante devient la pièce
                                    // précédente
        }
        return dungeonSoluce; // Retour de la solution donjon
    }
    @Override
    public String toString() {
        return "\nDungeon2Graph [dicoRoom=" + dicoRoom + "]";
    }
}
