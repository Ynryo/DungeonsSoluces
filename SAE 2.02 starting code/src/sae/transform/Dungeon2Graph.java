package sae.transform;

import java.util.HashMap;

import sae.dungeon.Dungeon;
import sae.dungeon.DungeonSoluce;
import sae.graph.Graph;
import sae.graph.Node;
import sae.solver.GraphSoluce;
import sae.dungeon.Room;
import java.util.Set;

public class Dungeon2Graph {

    private Dungeon dungeon;
    private HashMap<Room, Node> dicoRoom;

    // public Map<Room, Node> getDicoRoom() {
    // return dicoRoom;
    // }

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

        //create edges
        for (Room room : dungeon.getRooms()) {
            for (Room roomAdjacents : room.getNextRooms().values()) {
                if (!mappedNode(room).neighbors().contains(mappedNode(roomAdjacents))) {
                    graph.addEdge(mappedNode(room), mappedNode(roomAdjacents));
                    // System.out.println(String.format("Nombre de neighbors : %d", mappedNode(room).neighbors().size()));
                }
            }
        }
        System.out.println(graph);
    }

    public Node mappedNode(Room room) {
        return dicoRoom.get(room); //get from dico (already transform from rooms to nodes)
    }

    @Override
    public String toString() {
        return "\nDungeon2Graph [dicoRoom=" + dicoRoom + "]";
    }

    public DungeonSoluce transform(GraphSoluce soluceGraph) {
        return null;
        // transform la soluce du graph en dungeon (directions dans la console)
    }
}