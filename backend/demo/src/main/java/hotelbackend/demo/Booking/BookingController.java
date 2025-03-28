package hotelbackend.demo.Booking;

import java.sql.Date;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") // Allow React dev server
@RestController
@RequestMapping("/api/booking")
public class BookingController {
    
    private final BookingService bookingService = new BookingService();

    @PostMapping("/add/{id}")
    public void addBooking(@RequestBody String customerId, @RequestBody int roomId, @RequestBody Date checkinDate, @RequestBody Date checkoutDate) {
        try {
            bookingService.bookRoom(roomId, customerId, checkinDate, checkoutDate);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., log it or return an error response
        }
    }
}
