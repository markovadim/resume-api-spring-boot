package by.markov.resumeapispringboot.repository;

import by.markov.resumeapispringboot.entity.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO level for work with main object
 *
 * @author markov_vadim
 */

public interface ResumeRepository extends CrudRepository<Resume, Integer> {
    Resume findResumeByUser(String user);

    Page<Resume> findResumeByLocationContainsOrContactsContainsOrExperienceContains(String location, String contacts, String experience, Pageable pageable);

    Page<Resume> findAll(Pageable pageable);
}
