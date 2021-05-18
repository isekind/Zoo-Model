package animals;

public class Zebra extends Animal implements Enclosable {

    public Zebra() {
        super();
    }

    public Zebra(String name) {
        super(name);
    }

    /**
     * @param animal an instance of Enclosable
     * @return whether the given animal can live with a Zebra
     */
    public boolean isCompatibleWith(Animal animal) {
        return !(animal instanceof Lion);
    }

}
