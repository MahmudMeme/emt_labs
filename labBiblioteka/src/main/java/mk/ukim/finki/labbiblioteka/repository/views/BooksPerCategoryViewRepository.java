package mk.ukim.finki.labbiblioteka.repository.views;

import jakarta.transaction.Transactional;
import mk.ukim.finki.labbiblioteka.model.enumeration.Category;
import mk.ukim.finki.labbiblioteka.model.views.BooksPerCategoryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksPerCategoryViewRepository extends JpaRepository<BooksPerCategoryView, Category> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.books.per.category", nativeQuery = true)
    default void refreshMaterializedView() {
    }


}
