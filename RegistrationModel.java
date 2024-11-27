import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationModel extends Observable {
    private Map<String, Room> rooms = new HashMap<>();
    private Map<String, Booking> bookings = new HashMap<>();

    public void addRoom(Room room) {
        rooms.put(room.getRoomId(), room);
        notifyObservers("New room added: " + room.getRoomType() + ", Availability: " + room.isAvailable());
    }

    public void addBooking(Booking booking) {
        Room room = rooms.get(booking.getRoomId());
        if (room == null) {
            throw new IllegalArgumentException("Room ID " + booking.getRoomId() + " does not exist.");
        }
        if (room.isAvailable()) {
            bookings.put(booking.getBookingId(), booking);
            room.setAvailability(false);  // Set room as unavailable after booking
            notifyObservers("Booking added for " + booking.getGuestName());
        } else {
            throw new IllegalArgumentException("Room is not available for booking.");
        }
    }

    public void changeRoomAvailability(String roomId, boolean availability) {
        Room room = rooms.get(roomId);
        if (room == null) {
            throw new IllegalArgumentException("Room ID " + roomId + " does not exist.");
        }
        room.setAvailability(availability);
        notifyObservers("Room " + roomId + " availability changed to " + availability);
    }

    public List<String> listAllRooms() {
        return rooms.values().stream()
                .map(room -> room.getRoomId() + " - " + room.getRoomType() + " - Available: " + room.isAvailable())
                .collect(Collectors.toList());
    }

    public List<String> listBookings() {
        return bookings.values().stream()
                .map(booking -> "Booking ID: " + booking.getBookingId() + ", Room ID: " + booking.getRoomId() + ", Guest: " + booking.getGuestName() + ", Dates: " + booking.getCheckInDate() + " to " + booking.getCheckOutDate())
                .collect(Collectors.toList());
    }
}
