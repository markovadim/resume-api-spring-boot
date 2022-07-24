package by.markov.resumeapispringboot.exceptions;

/**
 * Custom exception (if resume by fields not found)
 *
 * @author markov_vadim
 */

public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException() {
        super("Resume Not Found.");
    }
}
