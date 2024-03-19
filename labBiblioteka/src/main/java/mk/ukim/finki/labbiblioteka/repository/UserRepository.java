package mk.ukim.finki.labbiblioteka.repository;

import mk.ukim.finki.labbiblioteka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
