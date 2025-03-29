package hotelbackend.demo.Rooms;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hotelbackend.demo.Customer.Customer;

@CrossOrigin(origins = "http://localhost:3000") // Allow React dev server
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService = new RoomService();

    @GetMapping("/search/filter")
    public List<Room> filterRooms(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam String chain, @RequestParam int maxPrice, @RequestParam int minPrice, @RequestParam int capacity, @RequestParam String city, @RequestParam String state, @RequestParam int rating) {
        System.out.println("Entering filterRooms with multiple parameters");
        System.out.println("Searching for rooms with parameters: " +
            "StartDate=" + startDate + ", EndDate=" + endDate + ", Chain=" + chain +
            ", MaxPrice=" + maxPrice + ", MinPrice=" + minPrice +
            ", Capacity=" + capacity + ", City=" + city + ", State=" + state +
            ", Rating=" + rating);
        List<Room> rooms = roomService.FilterRooms(startDate, endDate, chain, maxPrice, minPrice, capacity, city, state, rating);
        System.out.println("Found " + rooms.size() + " rooms");
        System.out.println("Exiting filterRooms with multiple parameters");
        return rooms;
    }

    @GetMapping("/search/roominfo/{id}")
    public Room filterRooms(@PathVariable int id) {
        System.out.println("Fetching Info for roomid: " + id);
        Room room = roomService.getRoomInfo(id);
        return room;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteRoom(@PathVariable int id) {
        try {
            return roomService.deleteRoom(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/edit/{id}")
    public boolean editRoom(@PathVariable int id, @RequestBody Room room) {
        try {
            return roomService.updateRoom(
                id,
                room.getView(),
                room.getAmentities(),
                room.getDamages()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
