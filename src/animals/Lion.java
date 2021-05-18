package animals;

public class Lion extends Animal implements Enclosable {

    public Lion() {
        super();
    }

    public Lion(String name) {
        super(name);
    }

    /**
     * @param animal an instance of Enclosable
     * @return whether the given animal can live with a Lion
     */
    public boolean isCompatibleWith(Animal animal) {
        return animal instanceof Lion;
    }

}
