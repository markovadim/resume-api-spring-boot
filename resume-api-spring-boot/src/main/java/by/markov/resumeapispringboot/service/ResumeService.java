package by.markov.resumeapispringboot.service;

import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.exceptions.ResumeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.ResumeNotFoundException;

import java.util.List;

public interface ResumeService {
    Iterable<Resume> showResumeList() throws Exception;

    void additionResume(Resume resume) throws ResumeAlreadyExistException;

    Resume findResumeById(Integer id) throws ResumeNotFoundException;

    Resume findResumeByUser(String user) throws ResumeNotFoundException;

    Resume deleteResume(Integer id) throws ResumeNotFoundException;

    Resume updateResume(Integer id, Resume newResume) throws ResumeNotFoundException;

    List<Resume> findResumeByLocationContainsOrContactsContainsOrExperienceContains(String location, String contacts, String experience);

    List<Resume> findResumeByLocationContainsAndContactsContainsAndExperienceContains(String location, String contacts, String experience);
}
