package animals;

public class Buzzard extends Animal implements Cagable {

    public Buzzard() {
        super();
    }

    public Buzzard(String name) {
        super(name);
    }

    /**
     * @param animal an instance of Cagable
     * @return whether the given animal can live with a Buzzard
     */
    public boolean isCompatibleWith(Animal animal) {
        return animal instanceof Buzzard;
    }

}
