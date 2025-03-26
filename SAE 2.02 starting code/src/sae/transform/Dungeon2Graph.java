package sae.transform;

import java.util.HashMap;

import sae.dungeon.Dungeon;
import sae.graph.Node;
import sae.dungeon.Room;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Dungeon2Graph {

    private Dungeon dungeon;
    private HashMap<Room, Node> dicoRoom;

    public Map<Room, Node> getDicoRoom() {
        return dicoRoom;
    }

    public Dungeon2Graph(Dungeon nDungeon) {
        dungeon = nDungeon;
        Set<Room> listRoom = dungeon.getRooms();
        dicoRoom = new HashMap<>(listRoom.size());
        Iterator<Room> it = listRoom.iterator();
        while (it.hasNext()) {
            Room key = it.next();
            Node value = new Node(key.getName(), key.getCoords());
            dicoRoom.put(key, value);
        }
    }

    public Node mappedNode(Room room) {
        return dicoRoom.get(room); //ne retourne rien (à si mtn c bon) 
        // return new Node(room.getName(), room.getCoords());
    }

    @Override
    public String toString() {
    return "\nDungeon2Graph [dicoRoom=" + dicoRoom + "]";
    }
}