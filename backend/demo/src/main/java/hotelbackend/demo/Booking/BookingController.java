package hotelbackend.demo.Booking;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") // Allow React dev server
@RestController
@RequestMapping("/api/booking")
public class BookingController {
    
    private final BookingService bookingService = new BookingService();

    @PostMapping("/add")
    public ResponseEntity<String> addBooking(@RequestBody Booking booking) {
        try {
            bookingService.bookRoom(
                booking.getRoomId(),
                String.valueOf(booking.getCustomerId()),
                new java.sql.Date(booking.getCheckinDate().getTime()),
                new java.sql.Date(booking.getCheckoutDate().getTime())
            );
            return ResponseEntity.ok("Booking added successfully");
        } catch (Exception e) {
            System.err.println("Error while adding booking: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error while adding booking: " + e.getMessage());
        }
    }

    @GetMapping("/availability/{roomId}")
    public ResponseEntity<List<List<Date>>> getRoomAvailability(@PathVariable int roomId) {
        try {
            List<List<Date>> availability = bookingService.getAvailability(roomId);
            System.out.println("Room availability: " + availability);
            return ResponseEntity.ok(availability);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(List.of()); // Return an empty list as a fallback
        }
    }
}
