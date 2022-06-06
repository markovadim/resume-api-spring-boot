package by.markov.resumeapispringboot.repository;

import by.markov.resumeapispringboot.entity.Resume;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO level for work with main object
 *
 * @author markov_vadim
 */

public interface ResumeRepository extends CrudRepository<Resume, Integer> {
    Resume findResumeByUser(String user);

    List<Resume> findResumeByLocationContainsOrContactsContainsOrExperienceContains(String location, String contacts, String experience);
}
