package lab6.exceptions;

/**
 * Исключение, которое выбрасывается, если введено слишком много аргументов. 
 * @author Alina
 */
public class TooManyArgumentsException extends Exception{
    public TooManyArgumentsException(String message) {
        super(message);
    }
}
