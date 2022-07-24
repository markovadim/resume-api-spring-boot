package by.markov.resumeapispringboot.exceptions;

/**
 * Custom exception (if resume by fields not found)
 *
 * @author markov_vadim
 */

public class ResumeNotFoundException extends Exception {

    public ResumeNotFoundException() {
        super("Resume Not Found.");
    }
}
