package io.github.qubaja.delaytrackerbackend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import io.github.qubaja.delaytrackerbackend.model.Stop;
import io.github.qubaja.delaytrackerbackend.repository.StopRepository;

@Service
public class StopService {

    private final StopRepository stopRepository;

    public StopService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    public List<Stop> getAllStops() {
        return stopRepository.findAll();
    }

    public Stop getStopByGtfsId(String gtfsStopId) {
        return stopRepository.findByGtfsStopId(gtfsStopId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stop not found"));
    }
}
