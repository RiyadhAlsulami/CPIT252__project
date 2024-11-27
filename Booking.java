import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Booking {
    private String bookingId;
    private String guestName;
    private String roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Booking(String bookingId, String guestName, String roomId, String checkInDateStr, String checkOutDateStr) {
        try {
            this.checkInDate = LocalDate.parse(checkInDateStr, FORMATTER);
            this.checkOutDate = LocalDate.parse(checkOutDateStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format, please use dd/MM/yyyy.");
        }

        if (this.checkOutDate.isBefore(this.checkInDate)) {
            throw new IllegalArgumentException("Check-out date cannot be before check-in date.");
        }

        this.bookingId = bookingId;
        this.guestName = guestName;
        this.roomId = roomId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getCheckInDate() {
        return checkInDate.format(FORMATTER);
    }

    public String getCheckOutDate() {
        return checkOutDate.format(FORMATTER);
    }
}
