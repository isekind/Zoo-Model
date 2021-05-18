package animals;

public class Seal extends Animal implements Aquable {

    public Seal() {
        super();
    }

    public Seal(String name) {
        super(name);
    }

    /**
     * @param animal an instance of Aquable
     * @return whether the given animal can live with a Seal
     */
    public boolean isCompatibleWith(Animal animal) {
        return !(animal instanceof Shark);
    }

}
