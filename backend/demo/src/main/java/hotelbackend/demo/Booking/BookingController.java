package hotelbackend.demo.Booking;

import java.sql.Date;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") // Allow React dev server
@RestController
@RequestMapping("/api/booking")
public class BookingController {
    
    private final BookingService bookingService = new BookingService();

    @PostMapping("/add")
public void addBooking(@RequestBody Booking booking) {
    try {
        bookingService.bookRoom(
            booking.getRoomId(),
            String.valueOf(booking.getCustomerId()),
            new java.sql.Date(booking.getCheckinDate().getTime()),
            new java.sql.Date(booking.getCheckoutDate().getTime())
        );
    } catch (Exception e) {
        e.printStackTrace();
        // Optionally return a ResponseEntity here
    }
}
}
