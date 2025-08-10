package afriqueMed.business;

import afriqueMed.domain.DTO.CreateTicketRequest;
import afriqueMed.domain.Ticket.Priority;
import afriqueMed.domain.Ticket.Status;
import afriqueMed.domain.Ticket.Ticket;
import afriqueMed.domain.equipement.Item;
import afriqueMed.domain.historyLog.ActionType;
import afriqueMed.domain.historyLog.HistoryLog;
import afriqueMed.domain.users.Client;
import afriqueMed.domain.users.User;
import afriqueMed.infra.equipmentrepos.ItemRepository;
import afriqueMed.infra.usersRepos.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@ApplicationScoped
public class ClientService {

    @Inject
    TicketService ticketService;
    @Inject
    HistoryLogService historyLogService;
    @Inject
    UserRepository userRepository;
    @Inject
    ItemRepository itemRepository;

    //tested
    @Transactional
    public Ticket createTicketForClient(Long clientId, CreateTicketRequest request) {
        // Get client
        User user = userRepository.findById(clientId);
        if (user == null) {
            System.out.println("User is null");
            throw new IllegalArgumentException("Client not found with ID: " + clientId);
        }

        //get item
        Item item = itemRepository.findById(request.itemId());
        if (item == null) {
            throw new IllegalArgumentException("Item not found with ID: " + request.itemId());
        }

        // Build ticket
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setTicketType(request.ticketType());
        ticket.setItem(item);
        ticket.setLocation(request.location());
        ticket.setDescription(request.description());
        ticket.setCountry(request.country());
        ticket.setDate(LocalDateTime.now());
        ticket.setStatus(Status.PENDING);
        ticket.setPriority(Priority.HIGH);


        // Save the ticket
        ticketService.createTicket(ticket);

        // Create a history log entry
        HistoryLog log = new HistoryLog();
        log.setTicket(ticket);
        log.setUser(user);
        log.setAction(ActionType.TICKET_CREATED);
        log.setLogMessage(
                "Ticket (ID: " + ticket.getId() + ") was created by client \"" + user.getName() +
                        "\" on " + LocalDateTime.now()
        );

        log.setTimestamp(LocalDateTime.now());

        // Save the history log
        historyLogService.createHistoryLog(log);
        return ticket;
    }
}
