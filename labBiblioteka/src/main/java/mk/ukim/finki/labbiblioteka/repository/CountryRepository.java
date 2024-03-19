package mk.ukim.finki.labbiblioteka.repository;

import mk.ukim.finki.labbiblioteka.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository< Country,Long> {
}
