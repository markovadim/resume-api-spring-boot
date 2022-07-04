package by.markov.resumeapispringboot.service;

import by.markov.resumeapispringboot.repository.ResumeRepository;
import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.exceptions.ResumeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Resume> findAll() throws ResumeNotFoundException {
        List<Resume> resumeList = resumeRepository.findAll();
        if (resumeList.isEmpty()) {
            throw new ResumeNotFoundException();
        }
        return resumeList;
    }

    public Resume addResume(Resume resume) {
        resumeRepository.save(resume);
        return resume;
    }

    public Resume findResumeById(Integer id) throws ResumeNotFoundException {
        return resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);
    }

    public Resume deleteResume(Integer id) throws ResumeNotFoundException {
        Resume resume = resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);
        resumeRepository.delete(resume);
        return resume;
    }

    /*
        This part for REST API application version from master branch.
     */

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

    public Page<Resume> findResumeByUserContainsOrLocationContainsOrContactsContainsOrExperienceContains(String user,
                                                                                                         String location,
                                                                                                         String contacts,
                                                                                                         String experience,
                                                                                                         Pageable pageable) {
        return resumeRepository.findResumeByUserContainsOrLocationContainsOrContactsContainsOrExperienceContains(user, location, contacts, experience, pageable);
    }
}
