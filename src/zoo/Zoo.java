package zoo;

import animals.Animal;
import areas.*;
import dataStructures.ICashCount;
import dataStructures.Money;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * A class representing a zoo. A zoo has Areas (including exactly
 * one Entrance) and a ticketMachine. The Areas have IDs.
 */
public class Zoo implements IZoo {

    private HashMap<Integer, Area> areas;
    private int nextId;
    private TicketMachine ticketMachine;

    public Zoo() {
        areas = new HashMap<>();
        areas.put(0, new Entrance());
        nextId = 1;
        ticketMachine = new TicketMachine();
    }

    public int addArea(IArea area) throws IllegalArgumentException {
        if (area instanceof Entrance) {
            throw new IllegalArgumentException("Cannot add another Entrance.");
        }
        validateAreaUnique(area);
        validateNotNull(area);

        areas.put(nextId, (Area) area);
        return nextId++;
    }

    public void removeArea(int areaId) throws IllegalArgumentException {
        validateAreaId(areaId);
        if (areaId == 0) {
            throw new IllegalArgumentException("Cannot remove the Entrance.");
        }

        areas.remove(areaId);
        disconnectAll(areaId);
    }

    /**
     * Removes all connections to the given area.
     */
    private void disconnectAll(int areaId) {
        for (int key : areas.keySet()) {
            areas.get(key).disconnect(areaId);
        }
    }

    public IArea getArea(int areaId) {
        validateAreaId(areaId);
        return areas.get(areaId);
    }

    private void validateAreaId(int areaId) throws IllegalArgumentException {
        if (!areas.containsKey(areaId)) {
            throw new IllegalArgumentException("Provided Area-ID doesn't exist.");
        }
    }

    private void validateAreaUnique(IArea area) throws IllegalArgumentException {
        validateNotNull(area);
        if (areas.containsValue(area)) {
            throw new IllegalArgumentException("Cannot add "
                    + "area that is already in the zoo.");
        }
    }

    public byte addAnimal(int areaId, Animal animal) {
        validateNotNull(animal);
        validateAreaId(areaId);
        validateAnimalUnique(animal);
        if (areas.get(areaId) instanceof Habitat) {
            return ((Habitat) areas.get(areaId)).addAnimal(animal);
        } else {
            return Codes.NOT_A_HABITAT;
        }
    }

    private void validateAnimalUnique(Animal animal) throws IllegalArgumentException {
        for (int key : areas.keySet()) {
            Area area = areas.get(key);
            if (area instanceof Habitat
                    && ((Habitat) area).contains(animal)) {
                throw new IllegalArgumentException("Cannot add an "
                        + "animal that is already in the zoo.");

            }
        }
    }

    private void validateNotNull(Object o) throws NullPointerException {
        if (o == null) {
            throw new NullPointerException("Cannot accept null as an argument.");
        }
    }


    /** INTERMEDIATE **/

    public void connectAreas(int fromAreaId, int toAreaId) {
        validateAreaId(fromAreaId);
        validateAreaId(toAreaId);
        areas.get(fromAreaId).connect(toAreaId);
    }

    public boolean isPathAllowed(ArrayList<Integer> areaIds) {
        validateNotNull(areaIds);
        for (int areaId : areaIds) {
            validateAreaId(areaId);
        }

        for (int i = 0; i < areaIds.size() - 1; i++) {
            if (!areConnected(areaIds.get(i), areaIds.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return whether the first area has a connection to the
     * second area
     */
    private boolean areConnected(int fromAreaId, int toAreaId) {
        return areas.get(fromAreaId).isConnected(toAreaId);
    }

    public ArrayList<String> visit(ArrayList<Integer> areaIdsVisited) {
        validateNotNull(areaIdsVisited);
        ArrayList<String> list = new ArrayList<>();
        if (isPathAllowed(areaIdsVisited)) {
            for (int areaId : areaIdsVisited) {
                if (areas.get(areaId) instanceof Habitat) {
                    ((Habitat) areas.get(areaId)).addAnimalsTo(list);
                }
            }
            return list;
        }
        return null;
    }

    public ArrayList<Integer> findUnreachableAreas() {
        ArrayList<Integer> reachableAreas = new ArrayList<>();
        listReachableAreas(0, reachableAreas);
        ArrayList<Integer> unreachable = new ArrayList<>(areas.keySet());
        unreachable.removeAll(reachableAreas);
        return unreachable;
    }

    /**
     * Adds all reachable areas to a list.
     *
     * Specifically, it adds all the areas the current area is
     * connected to and that are not already on the list.
     * Then it recursively calls itself for those new areas.
     * @param areaId the ID of the current area
     * @param reachableAreas the list of reachable areas
     */
    private void listReachableAreas(int areaId, ArrayList<Integer> reachableAreas) {
        reachableAreas.add(areaId);
        for (int nextAreaId : areas.get(areaId).getAdjacentAreas()) {
            if (!reachableAreas.contains(nextAreaId)) {
                listReachableAreas(nextAreaId, reachableAreas);
            }
        }
    }


    /** ADVANCED **/

    public void setEntranceFee(int pounds, int pence) {
        ticketMachine.setEntranceFee(pounds, pence);
    }

    public Money getEntranceFee() { // for testing
        return ticketMachine.getEntranceFee();
    }

    public void setCashSupply(ICashCount cash) {
        validateNotNull(cash);
        ticketMachine.setCashSupply(cash);
    }

    public ICashCount getCashSupply() {
        return ticketMachine.getCashSupply();
    }

    public ICashCount payEntranceFee(ICashCount cashInserted) {
        validateNotNull(cashInserted);
        return ticketMachine.payEntranceFee(cashInserted);
    }

}











