package cinemaabyss.events.cinema_abyss_events.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for {@link cinemaabyss.events.cinema_abyss_events.entity.User}
 */
public record UserDto(Integer id, String username, String email, String passwordHash, OffsetDateTime createdAt,
                      OffsetDateTime updatedAt) implements
    Serializable {

}
