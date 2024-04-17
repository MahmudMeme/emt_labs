package mk.ukim.finki.labbiblioteka.web.rest;

import mk.ukim.finki.labbiblioteka.model.Book;
import mk.ukim.finki.labbiblioteka.model.dto.BookDto;
import mk.ukim.finki.labbiblioteka.model.enumeration.Category;
import mk.ukim.finki.labbiblioteka.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.allBooks();
    }

    @GetMapping("/{id}")
    public Book findBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    //add
    @PostMapping("/add")
    public ResponseEntity<Book> addNewBook(@RequestBody BookDto bookDto) {
        return bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    //edit
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return bookService.update(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) {

        if (bookService.findBookById(id) != null) {
            bookService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    //changeUsable
    @PostMapping("/changeUsable/{id}")
    public ResponseEntity changeUsable(@PathVariable Long id) {
        bookService.changeUsable(id);
        if (bookService.findBookById(id) != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    //rent
    @PostMapping("/rent/{id}")
    public ResponseEntity rentBook(@PathVariable Long id) {
        bookService.rentBook(id);
        if (bookService.findBookById(id) != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/categories")
    public List<Category> listAllCategories() {
        Category[] category = Category.values();
        return Arrays.stream(category).toList();
    }

    @GetMapping("/search/{name}")
    public List<Book> searchByName(@PathVariable String name) {
        if (name.equals("") || name==null || name.isEmpty()){
            return bookService.allBooks();
        }
        return bookService.findAllByNameSearch(name);

    }

}
