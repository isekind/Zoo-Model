package areas;

import animals.Animal;
import animals.Cagable;
import zoo.Codes;

public class Cage extends Habitat {

    public Cage() {
        super();
    }

    public Cage(int capacity) {
        super(capacity);
    }

    public byte addAnimal(Animal animal) {
        if (animal instanceof Cagable) {
            return addHabitatAbleAnimal(animal);
        } else {
            return Codes.WRONG_HABITAT;
        }
    }

}
