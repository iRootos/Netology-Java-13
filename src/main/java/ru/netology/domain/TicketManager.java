package ru.netology.domain;

import java.util.Arrays;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void add(Ticket ticket) {
        repository.save(ticket);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public Ticket[] getAll() {
        return repository.getAll();
    }

    public Ticket[] searchFromTo(String from, String to) {
        Ticket[] tickets = repository.getAll();
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : tickets) {
            if (ticket.getDepartureAirport().equals(from) && ticket.getArrivalAirport().equals(to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < tmp.length - 1; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }
}
