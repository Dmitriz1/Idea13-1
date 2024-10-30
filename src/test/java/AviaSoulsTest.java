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
        Assertions.assertEquals(2, found.length);
        Assertions.assertEquals(300, found[0].getPrice()); // должен быть ticket2
        Assertions.assertEquals(500, found[1].getPrice()); // должен быть ticket1
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
        Assertions.assertEquals(3, found.length);
        Assertions.assertEquals(300, found[0].getPrice());
        Assertions.assertEquals(400, found[1].getPrice());
        Assertions.assertEquals(500, found[2].getPrice());
    }

    @Test
    public void testSortByFlightTime() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("Moscow", "Dubai", 500, 10, 14); // 4 hours
        Ticket ticket2 = new Ticket("Moscow", "Dubai", 300, 12, 20); // 8 hours
        Ticket ticket3 = new Ticket("Moscow", "Dubai", 400, 10, 16); // 6 hours
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] found = manager.searchAndSortBy("Moscow", "Dubai", new TicketTimeComparator());
        Assertions.assertEquals(3, found.length);
        Assertions.assertEquals(ticket1.getTimeTo() - ticket1.getTimeFrom(), found[0].getTimeTo() - found[0].getTimeFrom()); // 4 hours
        Assertions.assertEquals(ticket3.getTimeTo() - ticket3.getTimeFrom(), found[1].getTimeTo() - found[1].getTimeFrom()); // 6 hours
        Assertions.assertEquals(ticket2.getTimeTo() - ticket2.getTimeFrom(), found[2].getTimeTo() - found[2].getTimeFrom()); // 8 hours
    }
}