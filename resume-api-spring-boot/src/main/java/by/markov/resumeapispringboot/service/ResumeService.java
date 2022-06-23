package by.markov.resumeapispringboot.service;

import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.exceptions.ResumeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.ResumeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Application service for CRUD operations
 *
 * @author markov_vadim
 * @see ResumeServiceImpl - implemetnation this service
 */

public interface ResumeService {
    Page<Resume> showResumeList(Pageable pageable) throws ResumeNotFoundException;

    void additionResume(Resume resume) throws ResumeAlreadyExistException;

    Resume findResumeById(Integer id) throws ResumeNotFoundException;

    Resume deleteResume(Integer id) throws ResumeNotFoundException;

    Resume updateResume(Integer id, Resume newResume) throws ResumeNotFoundException;

    Page<Resume> findResumeByUserContainsOrLocationContainsOrContactsContainsOrExperienceContains(String user, String location, String contacts, String experience, Pageable pageable);
}
