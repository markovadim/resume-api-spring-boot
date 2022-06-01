package by.markov.resumeapispringboot.service;

import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.exceptions.ResumeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.ResumeNotFoundException;

public interface ResumeService {
    Iterable<Resume> showResumeList() throws Exception;

    void additionResume(Resume resume) throws ResumeAlreadyExistException;

    Resume getResume(Integer id) throws ResumeNotFoundException;

    Resume deleteResume(Integer id) throws ResumeNotFoundException;

    Resume updateResume(Integer id, Resume newResume) throws ResumeNotFoundException;
}
