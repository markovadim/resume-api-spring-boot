package by.markov.resumeapispringboot.service;


import by.markov.resumeapispringboot.repository.ResumeRepository;
import by.markov.resumeapispringboot.entity.Resume;
import by.markov.resumeapispringboot.exceptions.ResumeAlreadyExistException;
import by.markov.resumeapispringboot.exceptions.ResumeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    public Iterable<Resume> showResumeList() throws Exception {
        Iterable<Resume> resumeIterable = resumeRepository.findAll();
        if (!resumeIterable.iterator().hasNext()) {
            throw new Exception("Resume list is empty");
        } else {
            return resumeIterable;
        }
    }

    public Resume additionResume(Resume resume) throws ResumeAlreadyExistException {
        if (resumeRepository.findResumeByUser(resume.getUser()) != null) {
            throw new ResumeAlreadyExistException("Resume with name: " + resume.getUser() + " already exist.");
        }
        return resumeRepository.save(resume);
    }

    public Resume getResume(Integer id) throws ResumeNotFoundException {
        Resume resume = resumeRepository.findById(id).get();
        if (resume == null) {
            throw new ResumeNotFoundException("Resume with id:" + id + " was not found");
        }
        return resume;
    }

    public Resume deleteResume(Integer id) throws ResumeNotFoundException {
        Resume resume = resumeRepository.findById(id).get();
        if (resume == null) {
            throw new ResumeNotFoundException("Resume with id:" + id + " was not found");
        } else {
            resumeRepository.delete(resume);
        }
        return resume;
    }

    public Resume updateResume(Integer id, Resume newResume) throws ResumeNotFoundException {
        Resume resumeInDataBase = resumeRepository.findById(id).get();
        if (resumeInDataBase == null) {
            throw new ResumeNotFoundException("Resume with id:" + newResume.getId() + " was not found");
        } else {
            resumeInDataBase.setUser(newResume.getUser());
            resumeInDataBase.setLocation(newResume.getLocation());
            resumeInDataBase.setExperience(newResume.getExperience());
            resumeInDataBase.setContacts(newResume.getContacts());
            resumeRepository.save(resumeInDataBase);
        }
        return resumeInDataBase;
    }
}
