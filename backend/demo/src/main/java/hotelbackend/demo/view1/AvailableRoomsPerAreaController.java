package hotelbackend.demo.view1;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000") 
@RestController
@RequestMapping("/api/available-rooms")
public class AvailableRoomsPerAreaController {

    private final AvailableRoomsPerAreaService availableRoomsPerAreaService = new AvailableRoomsPerAreaService();

    @GetMapping
    public List<AvailableRoomsPerArea> getAvailableRoomsPerArea() {
        return availableRoomsPerAreaService.getAvailableRoomsPerArea();
    }
}
