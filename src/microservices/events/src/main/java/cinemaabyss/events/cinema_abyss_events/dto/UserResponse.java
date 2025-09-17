package cinemaabyss.events.cinema_abyss_events.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.OffsetDateTime;

/**
 * DTO for {@link cinemaabyss.events.cinema_abyss_events.entity.User}
 */
public record UserResponse(Integer user_id, String username, String action, Instant timestamp) implements
    Serializable {

}
