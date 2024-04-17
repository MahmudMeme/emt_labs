package mk.ukim.finki.labbiblioteka.service;

import mk.ukim.finki.labbiblioteka.model.Author;
import mk.ukim.finki.labbiblioteka.model.Book;
import mk.ukim.finki.labbiblioteka.model.dto.BookDto;
import mk.ukim.finki.labbiblioteka.model.enumeration.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BookService {
    Book create(String name, Category category, Author author, int availableCopies);

    Optional<Book> save(BookDto bookDto);

    Book update(Long id, String name, Category category, Author author, int availableCopies);

    Optional<Book> update(Long id, BookDto bookDto);

    void delete(Long id);

    List<Book> allBooks();

    void changeUsable(Long id);

    Book findBookById(Long id);

    void rentBook(Long id);
    void refreshMaterializedView();

    List<Book> findAllByNameSearch(String name);
}
