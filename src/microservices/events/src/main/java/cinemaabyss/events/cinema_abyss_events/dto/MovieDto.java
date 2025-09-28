package cinemaabyss.events.cinema_abyss_events.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

/**
 * DTO for {@link cinemaabyss.events.cinema_abyss_events.entity.Movie}
 */
public record MovieDto(Integer id, String title, String description, BigDecimal rating, LocalDate releaseDate,
                       Integer duration, String posterUrl, OffsetDateTime createdAt,
                       OffsetDateTime updatedAt) implements
    Serializable {

}
