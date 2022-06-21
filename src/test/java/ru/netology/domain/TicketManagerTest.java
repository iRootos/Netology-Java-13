package ru.netology.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketManagerTest {
    Ticket ticket1 = new Ticket(1, 1299, "SVO", "KZN", 180);
    Ticket ticket2 = new Ticket(2, 2199, "VKO", "KZN", 175);
    Ticket ticket3 = new Ticket(3, 5300, "SVO", "KZN", 160);
    Ticket ticket4 = new Ticket(4, 2399, "SVO", "KZN", 200);
    Ticket ticket5 = new Ticket(5, 5600, "LED", "KZN", 175);
    Ticket ticket6 = new Ticket(6, 5600, "LED", "KZN", 220);
    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);

    @BeforeEach
    public void setUp() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
    }

    @Test
    public void ShouldFindById() {
        Ticket expected = ticket2;
        assertEquals(repository.findById(2), expected);

    }

    @Test
    public void ShouldFindAndSortTickets1() {
        Ticket[] expected = {ticket1, ticket4, ticket3};
        assertArrayEquals(manager.searchFromTo("SVO", "KZN"), expected);
    }

    @Test
    public void ShouldFindAndSortTickets2() {
        Ticket[] expected = {ticket5, ticket6};
        assertArrayEquals(manager.searchFromTo("LED", "KZN"), expected);
    }

    @Test
    public void shouldNotFoundTicket() {
        Ticket[] expected = {};
        assertArrayEquals(manager.searchFromTo("SVO", "OMG"), expected);
    }

    @Test
    public void ShouldRemoveById() {
        manager.removeById(2);
        Ticket[] expected = {ticket1, ticket3, ticket4, ticket5, ticket6};
        assertArrayEquals(manager.getAll(), expected);
    }

    @Test
    public void ShouldRemoveByIdNotExists() {
        assertThrows(NotFoundException.class, () -> {
            manager.removeById(10);
        });
    }
}
