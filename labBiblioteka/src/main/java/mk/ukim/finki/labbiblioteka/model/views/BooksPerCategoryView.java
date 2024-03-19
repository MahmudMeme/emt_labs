package mk.ukim.finki.labbiblioteka.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import mk.ukim.finki.labbiblioteka.model.enumeration.Category;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM public.books.per.category")
@Immutable
public class BooksPerCategoryView {

    @Id
    private Category category;

    private Integer numberBooks;
}
