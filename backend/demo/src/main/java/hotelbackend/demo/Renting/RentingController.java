package hotelbackend.demo.Renting;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@CrossOrigin(origins = "http://localhost:3000") // Allow React dev server
@RestController
@RequestMapping("/api/renting")
public class RentingController {

    @Autowired
    private RentingService rentingService;

    @PostMapping("/convert")
    public void convertBooking(@RequestBody int bookingId) throws SQLException {
        rentingService.convertBooking(bookingId);
    }

    @PostMapping("/direct")
    public void addRenting(@RequestBody Renting renting) throws SQLException {
        rentingService.rentRoom(
            renting.getCustomerId(),
            renting.getRoomId(),
            new java.sql.Date(renting.getStartDate().getTime()),
            new java.sql.Date(renting.getEndDate().getTime()),
            true
        );
    }


    @GetMapping("/processpayment")
    public void getMethodName(@RequestParam int rentingId, @RequestParam int amount) throws SQLException {
        rentingService.updatePayment(rentingId, amount);
    }
    
}
