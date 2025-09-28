package cinemaabyss.events.cinema_abyss_events.mapper;

import cinemaabyss.events.cinema_abyss_events.dto.PaymentDto;
import cinemaabyss.events.cinema_abyss_events.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

  PaymentDto toDTO(Payment entity);

  Payment toEntity(PaymentDto dto);
}
