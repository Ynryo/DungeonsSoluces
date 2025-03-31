package sae.transform;

import java.util.HashMap;

import sae.dungeon.Dungeon;
import sae.dungeon.DungeonSoluce;
import sae.graph.Graph;
import sae.graph.Node;
import sae.solver.GraphSoluce;
import sae.dungeon.Room;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Dungeon2Graph {

    private Dungeon dungeon;
    private HashMap<Room, Node> dicoRoom;

    // public Map<Room, Node> getDicoRoom() {
    //     return dicoRoom;
    // }

    public Dungeon2Graph(Dungeon nDungeon) {
        dungeon = nDungeon;
        Set<Room> listRoom = dungeon.getRooms();
        dicoRoom = new HashMap<>(listRoom.size());
        Iterator<Room> it = listRoom.iterator();
        Graph graph = new Graph();
        while (it.hasNext()) {
            Room key = it.next();
            Node value = new Node(key.getName(), key.getCoords());
            dicoRoom.put(key, value);
            graph.addNode(value);
            // System.out.println("Room in dico " + );
            for (int i = 0; i < key.getNextRooms().values().size(); i++) {
                // System.out.println(key.getNextRooms());
                // System.out.println("--------------------------------------------");
                System.out.println(key.getNextRooms().values());
                System.out.println("--------------------------------------------");
                // System.out.println(key.getNextRooms().values().size());
                // System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
                // add neighbors to selected key/node
                for (Room room : key.getNextRooms().values()) {
                    graph.addEdge(value, mappedNode(room));
                }
            }
            // System.out.println("Dico size "+dicoRoom.size());
            // System.out.println("--------------------------------------------");
            // System.out.println("Nb dungeon rooms "+dungeon.getRooms());
            // System.out.println("00000000000000000000000000000000000000000000");
        }
        System.out.println(graph);

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