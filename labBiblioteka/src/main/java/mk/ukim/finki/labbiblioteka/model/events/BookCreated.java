package mk.ukim.finki.labbiblioteka.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDate;

@Getter
public class BookCreated extends ApplicationEvent {

    private LocalDate localDate;

    public BookCreated(Object source) {
        super(source);
        this.localDate = LocalDate.now();
    }
}
