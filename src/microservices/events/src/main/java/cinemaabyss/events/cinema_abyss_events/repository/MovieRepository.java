package cinemaabyss.events.cinema_abyss_events.repository;

import cinemaabyss.events.cinema_abyss_events.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
