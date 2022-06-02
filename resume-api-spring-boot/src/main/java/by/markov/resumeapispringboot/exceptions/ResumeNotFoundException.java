package by.markov.resumeapispringboot.exceptions;

public class ResumeNotFoundException extends Exception {

    public ResumeNotFoundException() {
        super("Resume Not Found.");
    }
}
