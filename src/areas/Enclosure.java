package areas;

import animals.Animal;
import animals.Enclosable;
import zoo.Codes;

public class Enclosure extends Habitat {

    public Enclosure() {
        super();
    }

    public Enclosure(int capacity) {
        super(capacity);
    }

    public byte addAnimal(Animal animal) {
        if (animal instanceof Enclosable) {
            return addHabitatAbleAnimal(animal);
        } else {
            return Codes.WRONG_HABITAT;
        }
    }

}
