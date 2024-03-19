package mk.ukim.finki.labbiblioteka.service.impl;

import mk.ukim.finki.labbiblioteka.model.Author;
import mk.ukim.finki.labbiblioteka.model.Book;
import mk.ukim.finki.labbiblioteka.model.dto.BookDto;
import mk.ukim.finki.labbiblioteka.model.enumeration.Category;
import mk.ukim.finki.labbiblioteka.repository.AuthorRepository;
import mk.ukim.finki.labbiblioteka.repository.BookRepository;
import mk.ukim.finki.labbiblioteka.repository.views.BooksPerCategoryViewRepository;
import mk.ukim.finki.labbiblioteka.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BooksPerCategoryViewRepository booksPerCategoryViewRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           BooksPerCategoryViewRepository booksPerCategoryViewRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.booksPerCategoryViewRepository = booksPerCategoryViewRepository;
    }

    @Override
    public Book create(String name, Category category, Author author, int availableCopies) {
        Book book = new Book(name, category, author, availableCopies);
        bookRepository.save(book);

        refreshMaterializedView();

        return book;
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow();

        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        bookRepository.save(book);

        refreshMaterializedView();


        return Optional.of(book);
    }

    @Override
    public Book update(Long id, String name, Category category, Author author, int availableCopies) {
        Book book = bookRepository.findById(id).orElseThrow();

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        bookRepository.save(book);

        return book;
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow();
        Book book = bookRepository.findById(id).orElseThrow();

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        if (book.isUsable())
            bookRepository.delete(book);

    }

    @Override
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void changeUsable(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setUsable(!book.isUsable());
        bookRepository.save(book);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public void rentBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        if (book.getAvailableCopies() > 0)
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

    }

    @Override
    public void refreshMaterializedView() {
        booksPerCategoryViewRepository.refreshMaterializedView();
    }
}
