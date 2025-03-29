package hotelbackend.demo.Hotel;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000") // Allow React dev server
@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    private final HotelService hotelService = new HotelService();

    @DeleteMapping("/delete/{id}")
    public boolean deleteHotel(@PathVariable int id) {
        try {
            return hotelService.deleteHotel(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/edit/{id}")
    public boolean editHotel(@PathVariable int id, @RequestBody Hotel hotel) {
        try {
            return hotelService.updateHotel(
                id,
                hotel.getHotelName(),
                hotel.getRating(),
                hotel.getHotelAddress()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
