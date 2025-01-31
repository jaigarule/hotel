import java.util.*;

// Room class to represent a hotel room
class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;
    private double price;

    public Room(int roomNumber, String category, boolean isAvailable, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Category: " + category + ", Price: $" + price + ", Available: " + isAvailable;
    }
}

// Booking class to represent a reservation
class Booking {
    private int bookingId;
    private String customerName;
    private Room room;

    public Booking(int bookingId, String customerName, Room room) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.room = room;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Customer Name: " + customerName + ", Room: " + room;
    }
}

// Main HotelReservationSystem class
public class HotelReservationSystem {
    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private int bookingCounter = 1;

    public HotelReservationSystem() {
        // Initialize some rooms
        rooms.add(new Room(101, "Single", true, 100));
        rooms.add(new Room(102, "Double", true, 150));
        rooms.add(new Room(103, "Suite", true, 300));
        rooms.add(new Room(104, "Double", true, 150));
    }

    public void searchRooms(String category) {
        System.out.println("Available rooms in category: " + category);
        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(category) && room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    public void makeReservation(String customerName, int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.setAvailable(false);
                Booking booking = new Booking(bookingCounter++, customerName, room);
                bookings.add(booking);
                System.out.println("Reservation successful! Booking details: " + booking);
                processPayment(room.getPrice());
                return;
            }
        }
        System.out.println("Room not available or invalid room number.");
    }

    public void viewBookings() {
        System.out.println("All Bookings:");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    private void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + "... Payment successful!");
    }

    public static void main(String[] args) {
        HotelReservationSystem system = new HotelReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Search Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter room category to search (Single/Double/Suite): ");
                    String category = scanner.nextLine();
                    system.searchRooms(category);
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter room number to book: ");
                    int roomNumber = scanner.nextInt();
                    system.makeReservation(name, roomNumber);
                    break;
                case 3:
                    system.viewBookings();
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}