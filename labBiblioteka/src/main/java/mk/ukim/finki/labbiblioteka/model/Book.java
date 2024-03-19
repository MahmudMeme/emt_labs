package mk.ukim.finki.labbiblioteka.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.labbiblioteka.model.enumeration.Category;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Author author;
    private int availableCopies;
    private boolean usable;

    public Book() {
    }

    public Book(String name, Category category, Author author, int availableCopies) {

        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.usable = false;
    }
}
