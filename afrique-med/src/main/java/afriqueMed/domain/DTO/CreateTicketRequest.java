package afriqueMed.domain.DTO;

import afriqueMed.domain.CountryEnum;
import afriqueMed.domain.Ticket.TicketType;

public record CreateTicketRequest(
        Long itemId,
        TicketType ticketType,
        String location,
        String description,
        CountryEnum country
) {}