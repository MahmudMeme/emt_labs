package mk.ukim.finki.labbiblioteka.model.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mk.ukim.finki.labbiblioteka.model.Author;
import mk.ukim.finki.labbiblioteka.model.enumeration.Category;

@Data
public class BookDto {

    private String name;

    private Category category;

    private Long authorId;

    private int availableCopies;


    public BookDto() {

    }

    public BookDto(String name, Category category, Long authorId, int availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;

    }
}
