package animals;

public class Parrot extends Animal implements Cagable {

    public Parrot() {
        super();
    }

    public Parrot(String name) {
        super(name);
    }

    /**
     * @param animal an instance of Cagable
     * @return whether the given animal can live with a Parrot
     */
    public boolean isCompatibleWith(Animal animal) {
        return animal instanceof Parrot;
    }

}
