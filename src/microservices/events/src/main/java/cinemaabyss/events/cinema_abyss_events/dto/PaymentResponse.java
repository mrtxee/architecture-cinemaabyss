package cinemaabyss.events.cinema_abyss_events.dto;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link cinemaabyss.events.cinema_abyss_events.entity.User}
 */
public record PaymentResponse(Integer payment_id, Integer user_id, Float amount, String status, Instant timestamp,
                              String method_type) implements
    Serializable {

}
