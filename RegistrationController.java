public class RegistrationController {
    private RegistrationModel model;

    public RegistrationController(RegistrationModel model) {
        this.model = model;
    }

    public void addRoom(String roomId, String roomType, boolean isAvailable) {
        Room newRoom = new Room(roomId, roomType, isAvailable);
        model.addRoom(newRoom);
    }

    public void createBooking(String bookingId, String guestName, String roomId, String checkInDate, String checkOutDate) {
        try {
            Booking newBooking = new Booking(bookingId, guestName, roomId, checkInDate, checkOutDate);
            model.addBooking(newBooking);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to create booking: " + e.getMessage());
        }
    }
}
