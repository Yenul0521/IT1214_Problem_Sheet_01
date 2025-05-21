// Ticket.java
class Ticket {
    private int ticketNumber;
    private String customerName;
    private int seatNumber;

    // Constructor
    public Ticket(int ticketNumber, String customerName, int seatNumber) {
        this.ticketNumber = ticketNumber;
        this.customerName = customerName;
        this.seatNumber = seatNumber;
    }

    // Getter methods
    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    @Override
    public String toString() {
        return "Ticket No: " + ticketNumber +
               ", Customer: " + customerName +
               ", Seat No: " + seatNumber;
    }
}

// MovieBookingSystem.java
class MovieBookingSystem {
    private final int MAX_SEATS = 10;
    private Ticket[] bookedTickets; // Stores tickets, indexed by seat number (seat 1 -> index 0)
    private boolean[] seatAvailability; // true if seat is booked, false if free
    private int nextTicketNumber; // Auto-increments for unique ticket IDs

    public MovieBookingSystem() {
        bookedTickets = new Ticket[MAX_SEATS]; // Array of size 10, for seats 1-10
        seatAvailability = new boolean[MAX_SEATS]; // All seats initially false (free)
        nextTicketNumber = 1; // Start ticket numbers from 1
    }

    /**
     * Books a ticket for a customer at a specific seat number.
     * @param customerName The name of the customer.
     * @param preferredSeatNumber The desired seat number (1 to MAX_SEATS).
     * @return The booked Ticket object if successful, null otherwise.
     */
    public Ticket bookTicket(String customerName, int preferredSeatNumber) {
        // Input validation for seat number
        if (preferredSeatNumber < 1 || preferredSeatNumber > MAX_SEATS) {
            System.out.println("Booking failed: Invalid seat number. Please choose a seat between 1 and " + MAX_SEATS + ".");
            return null;
        }

        int seatIndex = preferredSeatNumber - 1; // Convert seat number (1-based) to array index (0-based)

        // Check if the seat is already booked
        if (seatAvailability[seatIndex]) {
            System.out.println("Booking failed: Seat " + preferredSeatNumber + " is already booked.");
            return null;
        }

        // Check if all seats are booked
        // This is important because while a specific seat might be free, the array itself might be 'full' in terms of available slots
        // However, since we are mapping seat number to array index, this check is less critical here as we directly check seatAvailability.
        // A more general "is full" check would involve counting available seats.
        // For this scenario, since we have a direct mapping, it's sufficient to check seatAvailability[seatIndex].

        Ticket newTicket = new Ticket(nextTicketNumber, customerName, preferredSeatNumber);
        bookedTickets[seatIndex] = newTicket; // Store the ticket at the index corresponding to the seat
        seatAvailability[seatIndex] = true;   // Mark the seat as booked
        nextTicketNumber++;                   // Increment for the next ticket

        System.out.println("Booking successful: " + newTicket);
        return newTicket;
    }

    /**
     * Cancels a ticket given its ticket number, freeing the seat.
     * @param ticketNumberToCancel The ticket number to cancel.
     * @return true if cancellation was successful, false otherwise.
     */
    public boolean cancelTicket(int ticketNumberToCancel) {
        boolean found = false;
        int seatFreed = -1;

        // Iterate through all possible seat positions to find the ticket
        for (int i = 0; i < MAX_SEATS; i++) {
            if (bookedTickets[i] != null && bookedTickets[i].getTicketNumber() == ticketNumberToCancel) {
                seatFreed = bookedTickets[i].getSeatNumber(); // Get the seat number before nulling
                bookedTickets[i] = null;                      // Remove the ticket object
                seatAvailability[i] = false;                  // Mark the seat as free
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Cancellation successful: Ticket " + ticketNumberToCancel + " (Seat " + seatFreed + ") cancelled.");
            return true;
        } else {
            System.out.println("Cancellation failed: Ticket " + ticketNumberToCancel + " not found.");
            return false;
        }
    }

    /**
     * Displays details of all currently booked tickets.
     */
    public void displayAllBookings() {
        System.out.println("\n--- Current Bookings ---");
        boolean anyBooked = false;
        for (int i = 0; i < MAX_SEATS; i++) {
            if (bookedTickets[i] != null) {
                System.out.println(bookedTickets[i]);
                anyBooked = true;
            }
        }
        if (!anyBooked) {
            System.out.println("No tickets currently booked.");
        }
        System.out.println("------------------------\n");
    }
}

// Main.java
 class Main {
    public static void main(String[] args) {
        // Create a MovieBookingSystem instance
        MovieBookingSystem bookingSystem = new MovieBookingSystem();

        System.out.println("--- Initial Bookings ---");
        // Book tickets for three customers with ticket numbers 1, 2, and 3, assigned to seats 1, 2, and 3 respectively.
        bookingSystem.bookTicket("Alice", 1); // Ticket 1, Seat 1
        bookingSystem.bookTicket("Bob", 2);   // Ticket 2, Seat 2
        bookingSystem.bookTicket("Charlie", 3); // Ticket 3, Seat 3

        // Display all current bookings.
        bookingSystem.displayAllBookings();

        System.out.println("--- Cancellation ---");
        // Cancel the ticket with ticket number 2.
        bookingSystem.cancelTicket(2); // Should cancel Bob's ticket at Seat 2

        // Display all current bookings (to see the effect of cancellation).
        bookingSystem.displayAllBookings();

        System.out.println("--- Attempting New Booking for Free Seat ---");
        // Attempt to book a new ticket for a customer at seat 2 (which is now free).
        bookingSystem.bookTicket("David", 2); // Should be successful

        // Display all current bookings.
        bookingSystem.displayAllBookings();

        System.out.println("--- Testing Edge Cases ---");
        // Attempt to book an already taken seat
        bookingSystem.bookTicket("Eve", 1);

        // Attempt to book an invalid seat number
        bookingSystem.bookTicket("Frank", 0);
        bookingSystem.bookTicket("Grace", 11);

        // Fill up remaining seats
        bookingSystem.bookTicket("Harry", 4);
        bookingSystem.bookTicket("Ivy", 5);
        bookingSystem.bookTicket("Jack", 6);
        bookingSystem.bookTicket("Kelly", 7);
        bookingSystem.bookTicket("Liam", 8);
        bookingSystem.bookTicket("Mia", 9);
        bookingSystem.bookTicket("Noah", 10);

        // Attempt to book when full
        bookingSystem.bookTicket("Olivia", 5); // Seat 5 is already taken by Ivy
        bookingSystem.bookTicket("Pat", 1);    // Seat 1 is already taken by Alice

        bookingSystem.displayAllBookings();

        // Attempt to cancel a non-existent ticket
        bookingSystem.cancelTicket(99);
    }
}