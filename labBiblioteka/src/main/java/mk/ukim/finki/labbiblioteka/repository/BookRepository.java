package mk.ukim.finki.labbiblioteka.repository;

import mk.ukim.finki.labbiblioteka.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository< Book,Long> {
    List<Book> findAllByNameContaining(String name);
}
