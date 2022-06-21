package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket implements Comparable<Ticket> {
    private int id;
    private int price;
    private String departureAirport;
    private String arrivalAirport;
    private int time;

    @Override
    public int compareTo(Ticket otherTicket) {
        if (price < otherTicket.getPrice()) {
            return -1;
        }
        if (price > otherTicket.getPrice()) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && price == ticket.price && time == ticket.time && departureAirport.equals(ticket.departureAirport) && arrivalAirport.equals(ticket.arrivalAirport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, departureAirport, arrivalAirport, time);
    }
}
