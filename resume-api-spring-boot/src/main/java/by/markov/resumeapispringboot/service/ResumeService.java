package by.markov.resumeapispringboot.service;

import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.exceptions.ResumeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.ResumeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Application service for CRUD operations
 *
 * @author markov_vadim
 * @see ResumeServiceImpl - implemetnation this service
 */

public interface ResumeService {
    Page<Resume> findAll(Pageable pageable) throws ResumeNotFoundException;

    Resume findResumeById(Integer id) throws ResumeNotFoundException;

    Resume addResume(Resume resume) throws ResumeAlreadyExistException;

    void deleteResume(Integer id) throws ResumeNotFoundException;

    Resume updateResume(Integer id, Resume newResume) throws ResumeNotFoundException;

    List<Resume> findResumeByKeyword(String keyword);
}
