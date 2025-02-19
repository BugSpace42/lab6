package lab5.utility;

/**
 * Абстрактный класс команды.
 * @author Alina
 */
public abstract class Command implements Executable{
    private final String name;
    private final String description;
    
    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return название команды
     */
    public String getName() {
        return name;
    }

    /**
     * @return описание команды
     */
    public String getDescription() {
        return description;
    }
}

