import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RegistrationModel model = new RegistrationModel();
        RegistrationView view = new RegistrationView();
        RegistrationController controller = new RegistrationController(model);
        model.addObserver(view);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu Options:");
            System.out.println("1. Add Room");
            System.out.println("2. Make a Booking");
            System.out.println("3. List All Rooms");
            System.out.println("4. Change Room Availability");
            System.out.println("5. List All Bookings");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            try {
                switch (choice) {
                    case 1:
                        addRoom(scanner, controller);
                        break;
                    case 2:
                        makeBooking(scanner, controller);
                        break;
                    case 3:
                        model.listAllRooms().forEach(System.out::println);
                        break;
                    case 4:
                        changeRoomAvailability(scanner, model);
                        break;
                    case 5:
                        model.listBookings().forEach(System.out::println);
                        break;
                    case 6:
                        System.out.println("Exiting system.");
                        scanner.close();
                        System.exit(0);
                        return;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addRoom(Scanner scanner, RegistrationController controller) {
        System.out.print("Enter Room ID: ");
        String roomId = scanner.nextLine();
        System.out.print("Enter Room Type: ");
        String roomType = scanner.nextLine();
        boolean isAvailable = getBooleanInput(scanner, "Is the room available? (true/false): ");
        controller.addRoom(roomId, roomType, isAvailable);
    }

    private static void makeBooking(Scanner scanner, RegistrationController controller) {
        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();
        System.out.print("Enter Guest Name: ");
        String guestName = scanner.nextLine();
        System.out.print("Enter Room ID for Booking: ");
        String roomID = scanner.nextLine();
        System.out.print("Enter Check-In Date (dd/MM/yyyy): ");
        String checkInDate = scanner.nextLine();
        System.out.print("Enter Check-Out Date (dd/MM/yyyy): ");
        String checkOutDate = scanner.nextLine();
        controller.createBooking(bookingId, guestName, roomID, checkInDate, checkOutDate);
    }

    private static void changeRoomAvailability(Scanner scanner, RegistrationModel model) {
        System.out.print("Enter Room ID: ");
        String roomId = scanner.nextLine();
        boolean isAvailable = getBooleanInput(scanner, "Set availability (true for available, false for not available): ");
        model.changeRoomAvailability(roomId, isAvailable);
    }

    private static boolean getBooleanInput(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            } else {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
            }
        }
    }
}
