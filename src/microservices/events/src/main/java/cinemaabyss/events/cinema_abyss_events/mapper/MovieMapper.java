package cinemaabyss.events.cinema_abyss_events.mapper;

import cinemaabyss.events.cinema_abyss_events.entity.Movie;
import cinemaabyss.events.cinema_abyss_events.dto.MovieDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
  MovieDto toDTO(Movie movie);
  Movie toEntity(MovieDto movieDTO);
}
