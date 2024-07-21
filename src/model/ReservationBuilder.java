package src.model;

public class ReservationBuilder {
    private int hotelIndex;
    private String guestName;
    private int checkIn;
    private int checkOut;
    private int roomIndex;
    private String discountCode;

    public ReservationBuilder() {
        
    }

    public void setHotelIndex(int hotelIndex) {
        this.hotelIndex = hotelIndex;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }

    public void setRoomIndex(int roomIndex) {
        this.roomIndex = roomIndex;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public int getHotelIndex() {
        return this.hotelIndex;
    }

    public String getGuestName() {
        return this.guestName;
    }

    public int getCheckIn() {
        return this.checkIn;
    }

    public int getCheckOut() {
        return this.checkOut;
    }

    public int getRoomIndex() {
        return this.roomIndex;
    }

    public String getDiscountCode() {
        return this.discountCode;
    }
}
