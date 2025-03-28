package hotelbackend.demo.Rooms;


import hotelbackend.demo.Hotel.Hotel; // Import the Hotel class
import hotelbackend.demo.Hotel.HotelService;

public class Room {
    private int roomId;
    private int hotelId;
    private double price;
    private String view;
    private String amentities;
    private boolean extendable;
    private int capacity;
    private String damages;

    private String hotelName;
    private String hotelAddress;

    private Hotel hotel; // Add Hotel object

    // Getters and Setters
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getAmentities() {
        return amentities;
    }

    public void setAmentities(String amentities) {
        this.amentities = amentities;
    }

    public boolean isExtendable() {
        return extendable;
    }

    public void setExtendable(boolean extendable) {
        this.extendable = extendable;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDamages() {
        return damages;
    }

    public void setDamages(String damages) {
        this.damages = damages;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public Hotel getHotel() { // Getter for Hotel
        return hotel;
    }

    public void setHotel(Hotel hotel) { // Setter for Hotel
        this.hotel = hotel;
    }

    // Method to set Hotel using getHotelInfo
    public void setHotelInfo(int hotelId) {
        Hotel hotel = new Hotel(); // Assuming you have access to the existing getHotelInfo method elsewhere
        HotelService hotelService = new HotelService(); // Use HotelService to fetch hotel info
        hotel = hotelService.getHotelInfo(hotelId); // Call the method from HotelService
        this.setHotel(hotel);
        if (hotel != null) {
            this.setHotelName(hotel.getHotelName());
            this.setHotelAddress(hotel.getHotelAddress());
        }
    }
}
