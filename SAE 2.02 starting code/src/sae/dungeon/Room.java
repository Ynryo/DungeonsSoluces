package sae.dungeon;

import java.util.HashMap;
import java.util.Map;

public class Room {

	private String name;
	private Map<Direction, Room> nextRooms;
	private Coord coords;

	public Room(String name, Coord coords) {
		super();
		this.coords = coords;
		this.name = name;
		nextRooms = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public Coord getCoords() {
		return coords;
	}

	public Map<Direction, Room> getNextRooms() {
		return nextRooms;
	}

	public void addRoomToDirection(Room otherRoom, Direction direction) {
		if (nextRooms.get(direction) != null)
			return;
		nextRooms.put(direction, otherRoom);
		otherRoom.addRoomToDirection(this, direction.oppositeDirection());
	}

	@Override
	public String toString() {
        return String.format("%nRoom [name=%-2s, coord=%-7s, adjacent rooms: %s]", name, coords, nextRooms.values().size());
	}

}
