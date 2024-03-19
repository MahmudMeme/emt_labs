package mk.ukim.finki.labbiblioteka.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.labbiblioteka.model.Author;
import mk.ukim.finki.labbiblioteka.model.Book;
import mk.ukim.finki.labbiblioteka.model.Country;
import mk.ukim.finki.labbiblioteka.model.enumeration.Category;
import mk.ukim.finki.labbiblioteka.repository.AuthorRepository;
import mk.ukim.finki.labbiblioteka.repository.BookRepository;
import mk.ukim.finki.labbiblioteka.repository.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class dataHolder {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public dataHolder(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init() {
        List<Book> books = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        List<Country> countries = new ArrayList<>();

        if (countryRepository.count() == 0) {
            countries.add(new Country("makedonija", "evropa"));
            countries.add(new Country("usa", "amerika"));
            countryRepository.saveAll(countries);
        }
        if (authorRepository.count() == 0) {
            authors.add(new Author("make", "memedovski", countries.get(0)));
            authors.add(new Author("ana", "ttood", countries.get(0)));
            authorRepository.saveAll(authors);
        }
        if (bookRepository.count() == 0) {
            books.add(new Book("kniga1", Category.BIOGRAPHY, authors.get(0), 3));
            books.add(new Book("kniga2", Category.CLASSICS, authors.get(1), 30));
            books.add(new Book("kniga3", Category.FANTASY, authors.get(1), 2));
            books.add(new Book("kniga4", Category.CLASSICS, authors.get(0), 3));
            bookRepository.saveAll(books);
        }

    }
}
