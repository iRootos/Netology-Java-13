package ru.netology.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TicketManagerTest {
    Ticket ticket1 = new Ticket(1, 1299, "SVO", "KZN", "1ч. 35мин.");
    Ticket ticket2 = new Ticket(2, 2199, "VKO", "KZN", "1ч. 35мин.");
    Ticket ticket3 = new Ticket(3, 5300, "SVO", "KZN", "2ч. 45мин.");
    Ticket ticket4 = new Ticket(4, 2399, "SVO", "KZN", "4ч. 20мин.");
    Ticket ticket5 = new Ticket(5, 5600, "LED", "KZN", "2ч. 15мин.");
    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);

    @Test
    public void ShouldFindAndSortTickets() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        Ticket[] actual = manager.searchFromTo("SVO", "KZN");
        Ticket[] expected = {ticket1, ticket4, ticket3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldRemoveById() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.removeById(2);
        Ticket[] actual = manager.getAll();
        Ticket[] expected = {ticket1, ticket3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldRemoveByIdNotExists() {
        assertThrows(NotFoundException.class, () -> {
            manager.removeById(1);
        });
    }
}
