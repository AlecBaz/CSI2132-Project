package hotelbackend.demo.view2;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000") // Allow React dev server
@RestController
@RequestMapping("/api/hotel-room-capacity")
public class HotelRoomCapacityController {

    private final HotelRoomCapacityService hotelRoomCapacityService = new HotelRoomCapacityService();

    @GetMapping
    public List<HotelRoomCapacity> getHotelRoomCapacity() {
        return hotelRoomCapacityService.getHotelRoomCapacity();
    }
}
