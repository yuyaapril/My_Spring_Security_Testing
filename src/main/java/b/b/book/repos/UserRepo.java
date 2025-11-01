package b.b.book.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import b.b.book.models.AppUser;

@Repository //@Autowired
public interface UserRepo extends JpaRepository<AppUser, Long> {
  Optional<AppUser> findByUsername(String username);
}
