package mk.ukim.finki.labbiblioteka.service;

import mk.ukim.finki.labbiblioteka.model.Author;
import mk.ukim.finki.labbiblioteka.model.Book;
import mk.ukim.finki.labbiblioteka.model.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAuthors();

    Author findById(Long id);

   // public Optional<Book> findBookForAuthor(Long id);
    Author create(String name, String surname, Country country);
}
