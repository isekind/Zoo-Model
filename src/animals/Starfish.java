package animals;

public class Starfish extends Animal implements Aquable {

    public Starfish() {
        super();
    }

    public Starfish(String name) {
        super(name);
    }

    /**
     * @param animal an instance of Aquable
     * @return whether the given animal can live with a Starfish
     */
    public boolean isCompatibleWith(Animal animal) {
        return true;
    }

}
