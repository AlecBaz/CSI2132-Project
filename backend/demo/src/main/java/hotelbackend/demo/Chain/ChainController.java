package hotelbackend.demo.Chain;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") // Allow React dev server
@RestController
@RequestMapping("/api/chains")
public class ChainController {

    private final ChainService chainService = new ChainService();

    @GetMapping("/{id}")
    public HotelChain getChain(@PathVariable String id) {
        int intId = Integer.parseInt(id);
        return chainService.getChainByID(intId);
    }

    @GetMapping("/names")
    public List<String> getAllChainNames() {
        return chainService.getAllChainNames();
    }
}
