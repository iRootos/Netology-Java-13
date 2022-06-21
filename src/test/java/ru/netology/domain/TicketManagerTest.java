package ru.netology.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketManagerTest {
    Ticket ticket1 = new Ticket(1, 1299, "SVO", "KZN", 180);
    Ticket ticket2 = new Ticket(2, 2199, "VKO", "KZN", 175);
    Ticket ticket3 = new Ticket(3, 5300, "SVO", "KZN", 160);
    Ticket ticket4 = new Ticket(4, 2399, "SVO", "KZN", 200);
    Ticket ticket5 = new Ticket(5, 5600, "LED", "KZN", 175);
    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);

    @BeforeEach
    public void setUp() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
    }

    @Test
    public void ShouldFindById() {
        Ticket actual = repository.findById(2);
        Ticket expected = ticket2;
        assertEquals(expected, actual);

    }

    @Test
    public void ShouldFindAndSortTickets() {
        Ticket[] actual = manager.searchFromTo("SVO", "KZN");
        Ticket[] expected = new Ticket[]{ticket1, ticket4, ticket3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOneTicket() {
        Ticket[] actual = manager.searchFromTo("LED", "KZN");
        Ticket[] expected = new Ticket[]{ticket5};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldNotFoundTicket() {
        Ticket[] actual = manager.searchFromTo("SVO", "OMG");
        Ticket[] expected = new Ticket[]{};
        assertArrayEquals(actual, expected);
    }

    @Test
    public void ShouldRemoveById() {
        manager.removeById(2);
        Ticket[] actual = manager.getAll();
        Ticket[] expected = {ticket1, ticket3, ticket4, ticket5};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldRemoveByIdNotExists() {
        assertThrows(NotFoundException.class, () -> {
            manager.removeById(6);
        });
    }
}
