package mk.ukim.finki.labbiblioteka.web;

import mk.ukim.finki.labbiblioteka.model.Author;
import mk.ukim.finki.labbiblioteka.model.Book;
import mk.ukim.finki.labbiblioteka.model.enumeration.Category;
import mk.ukim.finki.labbiblioteka.service.AuthorService;
import mk.ukim.finki.labbiblioteka.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/", "/books"})
//@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.allBooks());
        model.addAttribute("bodyContent", "books");
        //return "test";
        return "master-template";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@RequestParam(required = false) String error, @PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/addNewBook")
    public String addNewBook(@RequestParam(required = false) String error, Model model) {
        List<Author> authorList = authorService.listAuthors();
        model.addAttribute("authorList", authorList);
        model.addAttribute("CategoryList", Category.values());
        model.addAttribute("bodyContent", "add-book");
        return "master-template";
    }

    @PostMapping("/addBook")
    public String addBook(@RequestParam(required = false) String error,
                          // @RequestParam Long id,
                          @RequestParam String name,
                          @RequestParam Category category,
                          @RequestParam Author author,
                          @RequestParam int availableCopies
    ) {
        bookService.create(name, category, author, availableCopies);

        return "redirect:/books";
    }

    @GetMapping("/edit-form/{id}")
    public String editBookPage(@PathVariable Long id, Model model) {
        List<Author> authorList = authorService.listAuthors();
        Book book = bookService.findBookById(id);
        model.addAttribute("currentBook", book);
        model.addAttribute("authorList", authorList);
        model.addAttribute("CategoryList", Category.values());
        model.addAttribute("bodyContent", "edit-book");
        return "master-template";
    }

    @PostMapping("/editBook")
    public String saveEdited(@RequestParam(required = false) String error,
                             @RequestParam Long id,
                             @RequestParam String name,
                             @RequestParam Category category,
                             @RequestParam Author author,
                             @RequestParam int availableCopies) {
        bookService.update(id, name, category, author, availableCopies);
        return "redirect:/books";
    }

    @PostMapping("/changeUsable/{id}")
    public String changeUsable(@RequestParam(required = false) String error, @PathVariable Long id) {
        bookService.changeUsable(id);
        return "redirect:/books";
    }

    @PostMapping("/rent/{id}")
    public String changeRanted(@RequestParam(required = false) String error, @PathVariable Long id) {
        bookService.rentBook(id);
        return "redirect:/books";
    }
}
