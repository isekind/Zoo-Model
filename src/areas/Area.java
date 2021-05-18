package areas;

import java.util.ArrayList;

/**
 * An abstract class for the Areas in the Zoo.
 * Subclasses are Entrance, PicnicArea, and Habitat.
 */
public abstract class Area implements IArea {

    protected ArrayList<Integer> adjacentAreaIds;

    public Area() {
        adjacentAreaIds = new ArrayList<>();
    }

    /**
     * Adds a one-way connection to the Area of the given ID.
     * If the connection already exists, does nothing.
     * @param toAreaId the ID of the destination Area
     */
    public void connect(int toAreaId) {
        if (!adjacentAreaIds.contains(toAreaId)) {
            adjacentAreaIds.add(toAreaId);
        }
    }

    /**
     * Removes the connection to the Area of the given ID.
     * If the connection doesn't exist, does nothing.
     * @param areaId the ID of the destination Area
     */
    public void disconnect(Integer areaId) {
        adjacentAreaIds.remove(areaId);
    }

    public boolean isConnected(int toAreaId) {
        return adjacentAreaIds.contains(toAreaId);
    }

    public ArrayList<Integer> getAdjacentAreas() {
        return adjacentAreaIds;
    }

}
