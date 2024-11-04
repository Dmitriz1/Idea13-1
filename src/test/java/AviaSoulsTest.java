import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AviaSoulsTest {
    @Test
    public void testAddAndSearch() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("Moscow", "Dubai", 500, 10, 16);
        Ticket ticket2 = new Ticket("Moscow", "Dubai", 300, 12, 18);
        manager.add(ticket1);
        manager.add(ticket2);

        Ticket[] found = manager.search("Moscow", "Dubai");

        int [] expected = {2, ticket2.getPrice(), ticket1.getPrice()};
        int [] actual = {found.length, found[0].getPrice(), found[1].getPrice()};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortByPrice() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("Moscow", "Dubai", 500, 10, 16);
        Ticket ticket2 = new Ticket("Moscow", "Dubai", 300, 12, 18);
        Ticket ticket3 = new Ticket("Moscow", "Dubai", 400, 12, 18);
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] found = manager.search("Moscow", "Dubai");

        int [] expected = {3, ticket2.getPrice(), ticket3.getPrice(), ticket1.getPrice()};
        int [] actual = {found.length, found[0].getPrice(), found[1].getPrice(), found[2].getPrice()};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortByFlightTime() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("Moscow", "Dubai", 500, 10, 14);
        Ticket ticket2 = new Ticket("Moscow", "Dubai", 300, 12, 20);
        Ticket ticket3 = new Ticket("Moscow", "Dubai", 400, 10, 16);
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] found = manager.searchAndSortBy("Moscow", "Dubai", new TicketTimeComparator());

        int[] expected = {3, ticket1.getTimeTo() - ticket1.getTimeFrom(),
                ticket3.getTimeTo() - ticket3.getTimeFrom(),
                ticket2.getTimeTo() - ticket2.getTimeFrom()};
        int[] actual = {found.length, found[0].getTimeTo() - found[0].getTimeFrom(),
                found[1].getTimeTo() - found[1].getTimeFrom(),
                found[2].getTimeTo() - found[2].getTimeFrom()};
        Assertions.assertArrayEquals(expected, actual);
    }
}