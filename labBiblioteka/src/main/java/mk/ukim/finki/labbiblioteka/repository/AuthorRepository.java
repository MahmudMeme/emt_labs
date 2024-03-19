package mk.ukim.finki.labbiblioteka.repository;

import mk.ukim.finki.labbiblioteka.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
