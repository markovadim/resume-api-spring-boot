package by.markov.resumeapispringboot.service;


import by.markov.resumeapispringboot.repository.ResumeRepository;
import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.exceptions.ResumeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.ResumeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation service interface
 *
 * @author markov_vadim
 */

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;

    public Page<Resume> showResumeList(Pageable pageable) throws ResumeNotFoundException {
        Page<Resume> resumeIterable = resumeRepository.findAll(pageable);
        if (resumeIterable.isEmpty()) {
            throw new ResumeNotFoundException();
        } else {
            return resumeIterable;
        }
    }

    public void additionResume(Resume resume) throws ResumeAlreadyExistException {
        if (resumeRepository.findResumeByUser(resume.getUser()) != null) {
            throw new ResumeAlreadyExistException();
        }else {
            resumeRepository.save(resume);
        }
    }

    public Resume findResumeById(Integer id) throws ResumeNotFoundException {
        Optional<Resume> resume = resumeRepository.findById(id);
        if (resume.isEmpty()) {
            throw new ResumeNotFoundException();
        }
        return resume.get();
    }

    public Resume findResumeByUser(String user) throws ResumeNotFoundException {
        Resume resume = resumeRepository.findResumeByUser(user);
        if (resume == null) {
            throw new ResumeNotFoundException();
        }
        return resume;
    }

    public void deleteResume(Integer id) throws ResumeNotFoundException {
        Optional<Resume> resume = resumeRepository.findById(id);
        if (resume.isEmpty()) {
            throw new ResumeNotFoundException();
        } else {
            resumeRepository.delete(resume.get());
        }
    }

    public Resume updateResume(Integer id, Resume newResume) throws ResumeNotFoundException {
        Optional<Resume> resumeInDataBase = resumeRepository.findById(id);
        if (resumeInDataBase.isEmpty()) {
            throw new ResumeNotFoundException();
        } else {
            resumeInDataBase.get().setUser(newResume.getUser());
            resumeInDataBase.get().setLocation(newResume.getLocation());
            resumeInDataBase.get().setExperience(newResume.getExperience());
            resumeInDataBase.get().setContacts(newResume.getContacts());
            resumeRepository.save(resumeInDataBase.get());
        }
        return resumeInDataBase.get();
    }

    public Page<Resume> findResumeByLocationContainsOrContactsContainsOrExperienceContains(String location, String contacts, String experience, Pageable pageable) {
        return resumeRepository.findResumeByLocationContainsOrContactsContainsOrExperienceContains(location, contacts, experience, pageable);
    }
}
