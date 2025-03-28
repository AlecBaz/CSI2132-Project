package hotelbackend.demo.Renting;

import java.util.Date;

public class Renting {
    private int rentingId;
    private int customerId;
    private int roomId; // Added roomId
    private Date startDate;
    private Date endDate;
    private String paymentStatus;

    // Getters and Setters
    public int getRentingId() {
        return rentingId;
    }

    public void setRentingId(int rentingId) {
        this.rentingId = rentingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomId() { // Getter for roomId
        return roomId;
    }

    public void setRoomId(int roomId) { // Setter for roomId
        this.roomId = roomId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
