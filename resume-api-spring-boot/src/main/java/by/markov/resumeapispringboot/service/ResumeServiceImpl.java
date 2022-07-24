package by.markov.resumeapispringboot.service;


import by.markov.resumeapispringboot.repository.ResumeRepository;
import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.exceptions.ResumeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.ResumeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation service interface
 *
 * @author markov_vadim
 */

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;

    public Page<Resume> findAll(Pageable pageable) throws ResumeNotFoundException {
        return resumeRepository.findAll(pageable);
    }

    public Resume addResume(Resume resume) throws ResumeAlreadyExistException {
        if (resumeRepository.findResumeByUser(resume.getUser()).isPresent()) {
            throw new ResumeAlreadyExistException();
        } else resumeRepository.saveAndFlush(resume);
        return resume;
    }

    public Resume findResumeById(Integer id) throws ResumeNotFoundException {
        return resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);
    }

    public void deleteResume(Integer id) throws ResumeNotFoundException {
        resumeRepository.delete(resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new));
    }

    public Resume updateResume(Integer id, Resume newResume) throws ResumeNotFoundException {
        Resume resumeInDataBase = resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);
        resumeInDataBase.setUser(newResume.getUser());
        resumeInDataBase.setLocation(newResume.getLocation());
        resumeInDataBase.setExperience(newResume.getExperience());
        resumeInDataBase.setContacts(newResume.getContacts());
        resumeRepository.save(resumeInDataBase);
        return resumeInDataBase;
    }

    public List<Resume> findResumeByKeyword(String keyword) {
        return resumeRepository.findAll().stream().filter(e -> e.toString().contains(keyword)).collect(Collectors.toList());
    }
}
