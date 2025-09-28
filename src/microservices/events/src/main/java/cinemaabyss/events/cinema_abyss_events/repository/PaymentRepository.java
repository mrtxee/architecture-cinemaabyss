package cinemaabyss.events.cinema_abyss_events.repository;

import cinemaabyss.events.cinema_abyss_events.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
