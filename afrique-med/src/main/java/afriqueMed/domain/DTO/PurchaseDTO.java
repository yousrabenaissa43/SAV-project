package afriqueMed.domain.DTO;

import java.time.LocalDate;

public record PurchaseDTO(
        Long clientId,
        Long itemId,
        LocalDate purchaseDate,
        LocalDate warrantyEndDate,
        String interventionSheet
) {}
