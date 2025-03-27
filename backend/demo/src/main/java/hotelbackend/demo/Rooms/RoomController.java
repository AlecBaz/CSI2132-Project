package hotelbackend.demo.Rooms;

import java.sql.Date;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") // Allow React dev server
@RestController
@RequestMapping("/api/rooms/search")
public class RoomController {

    private final RoomService roomService = new RoomService();

    @GetMapping("/filter")
    public List<Room> filterRooms(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam String chain, @RequestParam int maxPrice, @RequestParam int minPrice, @RequestParam int capacity, @RequestParam String city, @RequestParam String state, @RequestParam int rating) {
        System.out.println("Searching for rooms with parameters: " +
            "StartDate=" + startDate + ", EndDate=" + endDate + ", Chain=" + chain +
            ", MaxPrice=" + maxPrice + ", MinPrice=" + minPrice +
            ", Capacity=" + capacity + ", City=" + city + ", State=" + state +
            ", Rating=" + rating);
        List<Room> rooms = roomService.FilterRooms(startDate, endDate, chain, maxPrice, minPrice, capacity, city, state, rating);
        System.out.println("Found " + rooms.size() + " rooms");
        return rooms;
    }
}
