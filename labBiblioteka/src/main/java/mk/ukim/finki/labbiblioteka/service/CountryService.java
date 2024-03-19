package mk.ukim.finki.labbiblioteka.service;

import mk.ukim.finki.labbiblioteka.model.Author;
import mk.ukim.finki.labbiblioteka.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> lisCountries();

    Country findById(Long id);

    // public Optional<Book> findBookForAuthor(Long id);
    Country create(String name, String continent);
}
