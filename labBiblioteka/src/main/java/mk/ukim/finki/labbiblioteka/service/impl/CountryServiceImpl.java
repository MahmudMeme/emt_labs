package mk.ukim.finki.labbiblioteka.service.impl;

import mk.ukim.finki.labbiblioteka.model.Country;
import mk.ukim.finki.labbiblioteka.repository.CountryRepository;
import mk.ukim.finki.labbiblioteka.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> lisCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow();
    }

    @Override
    public Country create(String name, String continent) {
        Country country = new Country(name, continent);
        countryRepository.save(country);
        return country;
    }
}
