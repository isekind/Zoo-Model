package areas;

import animals.Animal;
import zoo.Codes;
import java.util.ArrayList;

/**
 * An abstract class for the Habitat Areas in the Zoo.
 * Subclasses are Aquarium, Cage, and Enclosure.
 */
public abstract class Habitat extends Area {

    protected ArrayList<Animal> animals;
    protected final int CAPACITY;

    public Habitat() {
        this(10);
    }

    public Habitat(int capacity) {
        super();
        animals = new ArrayList<>();
        CAPACITY = capacity;
    }

    /**
     * Tries to add an animal to the habitat.
     * @return a Code indicating if the animal was added or else
     * why not
     */
    public abstract byte addAnimal(Animal animal);

    /**
     * Takes an animal of the correct Habitat type and adds
     * it to the Habitat unless it's full or the other animals
     * are incompatible with the new animal.
     * @param animal an animal of the correct Habitat type
     * @return a Code indicating if the animal was added or else why not
     */
    protected byte addHabitatAbleAnimal(Animal animal) {
        if (isFull()) {
            return Codes.HABITAT_FULL;
        }
        if (!isCompatible(animal)) {
            return Codes.INCOMPATIBLE_INHABITANTS;
        }

        animals.add(animal);
        return Codes.ANIMAL_ADDED;
    }

    /**
     * Adds the names of all animals in this habitat to the list.
     * @param list a list of animal names
     */
    public void addAnimalsTo(ArrayList<String> list) {
        for (Animal animal : animals) {
            list.add(animal.getNickname());
        }
    }

    public boolean contains(Animal animal) {
        return animals.contains(animal);
    }

    protected boolean isFull() {
        return animals.size() == CAPACITY;
    }

    /**
     * Checks if an animal of the correct habitat type
     * could live with every animal already in the habitat.
     * @param newAnimal an animal of the correct habitat type
     * @return whether the animal could live with all animals in this habitat
     */
    protected boolean isCompatible(Animal newAnimal) {
        for (Animal animal : animals) {
            if (!animal.isCompatibleWith(newAnimal)) {
                return false;
            }
        }
        return true;
    }

}
