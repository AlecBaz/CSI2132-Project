package hotelbackend.demo.Employee;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
    private int employeeId;
    private String employeeName;
    private String employeeAddress;

    @JsonProperty("SIN_num")
    private int SIN_num; 

    private int hotelId;
    private String position;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public int getSIN_num() { 
        return SIN_num;
    }

    public void setSIN_num(int SIN_num) { 
        this.SIN_num = SIN_num;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
