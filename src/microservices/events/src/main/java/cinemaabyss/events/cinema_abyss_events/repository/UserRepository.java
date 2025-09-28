package cinemaabyss.events.cinema_abyss_events.repository;

import cinemaabyss.events.cinema_abyss_events.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
