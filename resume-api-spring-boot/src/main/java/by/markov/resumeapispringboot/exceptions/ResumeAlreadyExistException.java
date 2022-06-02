package by.markov.resumeapispringboot.exceptions;

public class ResumeAlreadyExistException extends Exception{

    public ResumeAlreadyExistException() {
        super("Resume Already Exist.");
    }
}
