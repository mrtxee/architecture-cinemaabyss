package cinemaabyss.events.cinema_abyss_events.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payments_id_gen")
  @SequenceGenerator(name = "payments_id_gen", sequenceName = "payments_id_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "amount", nullable = false, precision = 10, scale = 2)
  private BigDecimal amount;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "\"timestamp\"")
  private OffsetDateTime timestamp;

  @ColumnDefault("'completed'")
  @Column(name = "status", length = 50)
  private String status;

  @Column(name = "payment_method", length = 50)
  private String paymentMethod;

  @Column(name = "transaction_id")
  private String transactionId;

}
