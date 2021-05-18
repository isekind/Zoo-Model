package animals;

/**
 * You can modify the contents of this class, but you cannot:
 * - change the name, parameters or return types of provided methods
 * - remove it entirely
 */
public abstract class Animal {

	protected String name;

	public Animal() {
		this("Der Shredder");
	}

	public Animal(String name) {
		this.name = name;
	}

	/**
	 * @return Returns this animal's given name.
	 */
	public String getNickname() {
		return name;
	}
	
	/**
	 * Check whether two animals can live together.
	 * @param animal The animal for which to check compatibility with this animal.
	 * @return Returns true for compatible animals and false otherwise.
	 */
	public abstract boolean isCompatibleWith(Animal animal);

}
