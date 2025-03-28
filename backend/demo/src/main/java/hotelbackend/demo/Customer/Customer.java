package hotelbackend.demo.Customer;

public class Customer {
    private int customerId;
    private String idType;
    private String idNumber; // New field added
    private String customerAddress;
    private String customerName;
    private java.sql.Date registrationDate;

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() { // Getter for idNumber
        return idNumber;
    }

    public void setIdNumber(String idNumber) { // Setter for idNumber
        this.idNumber = idNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public java.sql.Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(java.sql.Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
