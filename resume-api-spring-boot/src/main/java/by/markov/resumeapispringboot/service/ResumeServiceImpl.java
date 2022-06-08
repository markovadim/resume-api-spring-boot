package by.markov.resumeapispringboot.service;


import by.markov.resumeapispringboot.repository.ResumeRepository;
import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.exceptions.ResumeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.ResumeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation service interface
 *
 * @author markov_vadim
 */

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    public Page<Resume> showResumeList(Pageable pageable) throws Exception {
        Page<Resume> resumeIterable = resumeRepository.findAll(pageable);
        if (!resumeIterable.iterator().hasNext()) {
            throw new Exception("Resume list is empty");
        } else {
            return resumeIterable;
        }
    }

    public void additionResume(Resume resume) throws ResumeAlreadyExistException {
        if (resumeRepository.findResumeByUser(resume.getUser()) != null) {
            throw new ResumeAlreadyExistException();
        }
        resumeRepository.save(resume);
    }

    public Resume findResumeById(Integer id) throws ResumeNotFoundException {
        Resume resume = resumeRepository.findById(id).get();
        if (resume == null) {
            throw new ResumeNotFoundException();
        }
        return resume;
    }

    public Resume findResumeByUser(String user) throws ResumeNotFoundException {
        Resume resume = resumeRepository.findResumeByUser(user);
        if (resume == null) {
            throw new ResumeNotFoundException();
        }
        return resume;
    }

    public Resume deleteResume(Integer id) throws ResumeNotFoundException {
        Resume resume = resumeRepository.findById(id).get();
        if (resume == null) {
            throw new ResumeNotFoundException();
        } else {
            resumeRepository.delete(resume);
        }
        return resume;
    }

    public Resume updateResume(Integer id, Resume newResume) throws ResumeNotFoundException {
        Resume resumeInDataBase = resumeRepository.findById(id).get();
        if (resumeInDataBase == null) {
            throw new ResumeNotFoundException();
        } else {
            resumeInDataBase.setUser(newResume.getUser());
            resumeInDataBase.setLocation(newResume.getLocation());
            resumeInDataBase.setExperience(newResume.getExperience());
            resumeInDataBase.setContacts(newResume.getContacts());
            resumeRepository.save(resumeInDataBase);
        }
        return resumeInDataBase;
    }

    public Page<Resume> findResumeByLocationContainsOrContactsContainsOrExperienceContains(String location, String contacts, String experience, Pageable pageable) {
        return resumeRepository.findResumeByLocationContainsOrContactsContainsOrExperienceContains(location, contacts, experience, pageable);
    }
}
