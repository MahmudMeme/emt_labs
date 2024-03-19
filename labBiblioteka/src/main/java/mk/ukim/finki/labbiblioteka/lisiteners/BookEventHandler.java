package mk.ukim.finki.labbiblioteka.lisiteners;

import mk.ukim.finki.labbiblioteka.model.events.BookCreated;
import mk.ukim.finki.labbiblioteka.service.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookEventHandler {

    private final BookService bookService;

    public BookEventHandler(BookService bookService) {
        this.bookService = bookService;
    }

    @EventListener
    public void onBookCreated(BookCreated event) {
        bookService.refreshMaterializedView();
    }
}
