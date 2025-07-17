package afriqueMed.business;

import afriqueMed.domain.Ticket.Ticket;
import afriqueMed.infra.operations.TicketRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TicketService {

    @Inject
    TicketRepository ticketRepository;

    @Transactional
    public void createTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Transactional
    public void deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id);
        if (ticket != null) {
            ticketRepository.delete(ticket);
        }
    }

}
