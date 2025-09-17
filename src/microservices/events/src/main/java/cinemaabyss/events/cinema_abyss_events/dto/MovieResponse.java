package cinemaabyss.events.cinema_abyss_events.dto;

import java.io.Serializable;

/**
 * DTO for {@link cinemaabyss.events.cinema_abyss_events.entity.User}
 */
public record MovieResponse(Integer user_id, String title, String action, Integer movie_id, String status) implements
    Serializable {

}
