package animals;

public class Shark extends Animal implements Aquable {

    public Shark() {
        super();
    }

    public Shark(String name) {
        super(name);
    }

    /**
     * @param animal an instance of Aquable
     * @return whether the given animal can live with a Shark
     */
    public boolean isCompatibleWith(Animal animal) {
        return !(animal instanceof Seal);
    }

}
