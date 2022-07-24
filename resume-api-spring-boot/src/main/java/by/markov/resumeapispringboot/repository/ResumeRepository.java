package by.markov.resumeapispringboot.repository;

import by.markov.resumeapispringboot.entity.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * DAO level for work with main object
 *
 * @author markov_vadim
 */

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    Optional<Resume> findResumeByUser(String user);

    Page<Resume> findAll(Pageable pageable);
}
