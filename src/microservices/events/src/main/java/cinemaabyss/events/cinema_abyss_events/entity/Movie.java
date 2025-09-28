package cinemaabyss.events.cinema_abyss_events.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movies_id_gen")
  @SequenceGenerator(name = "movies_id_gen", sequenceName = "movies_id_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

  @Column(name = "rating", precision = 3, scale = 1)
  private BigDecimal rating;

  @Column(name = "release_date")
  private LocalDate releaseDate;

  @Column(name = "duration")
  private Integer duration;

  @Column(name = "poster_url")
  private String posterUrl;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "created_at")
  private OffsetDateTime createdAt;

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

}
