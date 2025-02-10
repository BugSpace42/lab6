package lab5.utility;

/**
 * Абстрактный класс команды.
 * @author Alina
 */
public abstract class Command implements Executable{
    private String name;
    private String description;
    private int minNumberOfArguments;
    private int maxNumberOfArguments;
    
    public Command(String name, String description, int minNumberOfArguments, int maxNumberOfArguments) {
        this.name = name;
        this.description = description;
        this.minNumberOfArguments = minNumberOfArguments;
        this.maxNumberOfArguments = maxNumberOfArguments;
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

    /**
     * @return наименьшее количество аргументов команды
     */
    public int getMinNumberOfArguments() {
        return minNumberOfArguments;
    }

    /**
     * @return наибольшее количество аргументов команды
     */
    public int getMaxNumberOfArguments() {
        return maxNumberOfArguments;
    }
}

