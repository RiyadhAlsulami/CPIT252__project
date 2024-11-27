public class Room {
    private String roomId;
    private String roomType;
    private boolean isAvailable;

    public Room(String roomId, String roomType, boolean isAvailable) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
