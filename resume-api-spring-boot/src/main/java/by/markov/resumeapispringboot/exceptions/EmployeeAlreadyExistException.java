package by.markov.resumeapispringboot.exceptions;

/**
 * Custom exception (if username already exist in database)
 *
 * @author markov_vadim
 */

public class EmployeeAlreadyExistException extends Exception {

    public EmployeeAlreadyExistException() {
        super("Resume Already Exist.");
    }
}
