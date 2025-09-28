package cinemaabyss.events.cinema_abyss_events.dto;

import cinemaabyss.events.cinema_abyss_events.entity.Payment;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * DTO for {@link Payment}
 */
public record PaymentDto(Integer id, UserDto user, BigDecimal amount, OffsetDateTime timestamp, String status,
                         String paymentMethod, String transactionId) implements
    Serializable {

}
