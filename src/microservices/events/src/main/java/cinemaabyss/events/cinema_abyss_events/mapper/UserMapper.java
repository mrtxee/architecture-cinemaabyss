package cinemaabyss.events.cinema_abyss_events.mapper;

import cinemaabyss.events.cinema_abyss_events.dto.UserDto;
import cinemaabyss.events.cinema_abyss_events.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto toDTO(User entity);

  User toEntity(UserDto dto);
}
