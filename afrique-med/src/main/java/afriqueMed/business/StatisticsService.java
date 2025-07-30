package afriqueMed.business;

import afriqueMed.domain.Ticket.Intervention;
import afriqueMed.domain.Ticket.Status;
import afriqueMed.domain.Ticket.TicketType;
import afriqueMed.domain.equipement.ItemStatus;
import afriqueMed.infra.equipmentrepos.ItemRepository;
import afriqueMed.infra.operations.HistoryLogRepository;
import afriqueMed.infra.operations.InterventionRepository;
import afriqueMed.infra.operations.TicketRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class StatisticsService {

    @Inject
    TicketRepository ticketRepository;

    @Inject
    InterventionRepository interventionRepository;

    @Inject
    ItemRepository itemRepository;

    @Inject
    HistoryLogRepository historyLogRepository;

    // gTotal number of tickets
    public long getTotalTickets() {
        return ticketRepository.findAll().size();
    }

    // Number of tickets per status
    public Map<Status, Long> getTicketCountPerStatus() {
        return Arrays.stream(Status.values())
                .collect(Collectors.toMap(
                        status -> status,
                        status -> (long) ticketRepository.findByStatus(status).size()
                ));
    }

    // Interventions per technician (by full name)
    public Map<String, Long> getInterventionsPerTechnician() {
        return interventionRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        i -> i.getTechnician().getName(),
                        Collectors.counting()
                ));
    }

    // Ticket count per type
    public Map<TicketType, Long> getTicketCountPerType() {
        return ticketRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        t -> t.getTicketType(),
                        Collectors.counting()
                ));
    }

    // Interventions per day between dates
    public Map<LocalDate, Long> getInterventionsPerDay(LocalDate start, LocalDate end) {
        return interventionRepository.findAll()
                .stream()
                .filter(i -> {
                    LocalDate date = i.getStartDate().toLocalDate();
                    return !date.isBefore(start) && !date.isAfter(end);
                })
                .collect(Collectors.groupingBy(
                        i -> i.getStartDate().toLocalDate(),
                        Collectors.counting()
                ));
    }

    // Items marked as 'To be decommissioned'
    public long countDecommissionedItems() {
        return itemRepository.findAll()
                .stream()                                      //mistyped / to be corrected later
                .filter(item -> item.getItemStatus() == ItemStatus.DECOMMISSONNED)
                .count();
    }


    //  Action counts by type
    public Map<String, Long> getActionCountsByType() {
        return historyLogRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        h -> h.getAction().name(),
                        Collectors.counting()
                ));
    }

    // Top clients by ticket count
    public Map<String, Long> getTopClientsByTicketCount(int topN) {
        return ticketRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        t -> t.getUser().getName(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(topN)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    // Average resolution time (hours)
    public double getAverageResolutionTimeHours() {
        return interventionRepository.findAll()
                .stream()
                .filter(i -> i.isDone() && i.getStartDate() != null && i.getEndDate() != null)
                .mapToDouble(i -> ChronoUnit.HOURS.between(i.getStartDate(), i.getEndDate()))
                .average()
                .orElse(0.0);
    }

    //  Monthly ticket trend
    public Map<String, Long> getMonthlyTicketTrends(int monthsBack) {
        LocalDate now = LocalDate.now();
        LocalDate start = now.minusMonths(monthsBack);

        return ticketRepository.findAll()
                .stream()
                .filter(t -> t.getDate().toLocalDate().isAfter(start))
                .collect(Collectors.groupingBy(
                        t -> t.getDate().getMonth() + " " + t.getDate().getYear(),
                        Collectors.counting()
                ));
    }
    public double getAverageInterventionsPerDay(LocalDate start, LocalDate end) {
        List<Intervention> interventions = interventionRepository.findByStartDateBetween(start.atStartOfDay(), end.plusDays(1).atStartOfDay());
        long daysBetween = ChronoUnit.DAYS.between(start, end) + 1; // include both start and end days
        return daysBetween == 0 ? 0 : (double) interventions.size() / daysBetween;
    }


}

