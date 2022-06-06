package by.markov.resumeapispringboot.exceptions;

/**
 * Custom exception (if username already exist in database)
 *
 * @author markov_vadim
 */

public class ResumeAlreadyExistException extends Exception {

    public ResumeAlreadyExistException() {
        super("Resume Already Exist.");
    }
}
