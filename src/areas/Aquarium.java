package areas;

import animals.Animal;
import animals.Aquable;
import zoo.Codes;

public class Aquarium extends Habitat {

    public Aquarium() {
        super();
    }

    public Aquarium(int capacity) {
        super(capacity);
    }

    public byte addAnimal(Animal animal) {
        if (animal instanceof Aquable) {
            return addHabitatAbleAnimal(animal);
        } else {
            return Codes.WRONG_HABITAT;
        }
    }

}
