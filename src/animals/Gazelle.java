package animals;

public class Gazelle extends Animal implements Enclosable {

    public Gazelle() {
        super();
    }

    public Gazelle(String name) {
        super(name);
    }

    /**
     * @param animal an instance of Enclosable
     * @return whether the given animal can live with a Gazelle
     */
    public boolean isCompatibleWith(Animal animal) {
        return !(animal instanceof Lion);
    }

}
