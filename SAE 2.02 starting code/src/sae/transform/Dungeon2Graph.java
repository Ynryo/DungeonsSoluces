package sae.transform;

import java.util.HashMap;

import sae.dungeon.Dungeon;
import sae.dungeon.DungeonSoluce;
import sae.graph.Graph;
import sae.graph.Node;
import sae.solver.GraphSoluce;
import sae.dungeon.Room;
import java.util.Iterator;
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
        Iterator<Room> it = listRoom.iterator();
        Graph graph = new Graph();
        while (it.hasNext()) {
            Room key = it.next();
            Node value = new Node(key.getName(), key.getCoords());
            dicoRoom.put(key, value);
            graph.addNode(value);
        }

        
    }
        /*
         * for (Room room : listRoom) {
         * Node node = new Node(room.getName(), room.getCoords());
         * dicoRoom.put(room, node);
         * }
         */
        // faut créer un graph (new Graph())
    }

    public Node mappedNode(Room room) {
        return dicoRoom.get(room); // ne retourne rien (à si mtn c bon)
        // return new Node(room.getName(), room.getCoords());
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