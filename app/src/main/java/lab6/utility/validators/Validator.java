package lab6.utility.validators;

/**
 * Проверяет корректность данных определённого типа, введённных пользователем.
 * @author Alina
 */
public interface Validator<T> {
    /**
     * Проверяет корректность данных, введённных пользователем
     * @param value значение для проверки
     * @return true - если значение корректное, иначе false
     */
    boolean validate(T value);
}
