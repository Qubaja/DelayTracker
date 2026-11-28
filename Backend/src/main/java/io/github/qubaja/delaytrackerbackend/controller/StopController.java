package io.github.qubaja.delaytrackerbackend.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.qubaja.delaytrackerbackend.model.Stop;
import io.github.qubaja.delaytrackerbackend.repository.StopRepository;
import io.github.qubaja.delaytrackerbackend.service.StopService;
@RestController
@RequestMapping("/api/stops")
public class StopController {

    private final StopRepository stopRepository;
    private final StopService stopService;
    public StopController(StopRepository stopRepository, StopService stopService) {
        this.stopRepository = stopRepository;
        this.stopService=stopService;
    }

    @GetMapping("/all")
    public List<Stop> getStops() {
        return stopRepository.findAll();
    }

    @GetMapping("/{gtfsStopId}")
    public Stop findbyGtfsid(@PathVariable String gtfsStopId) {
       return stopService.getStopByGtfsId(gtfsStopId);

    }
}
