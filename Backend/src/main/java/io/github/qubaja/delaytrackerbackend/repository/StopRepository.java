package io.github.qubaja.delaytrackerbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.qubaja.delaytrackerbackend.model.Stop;

public interface StopRepository extends JpaRepository<Stop, Integer> {

    public Optional<Stop> findByGtfsStopId(String gtfsStopId);
    public List<Stop> findByNameContainingIgnoreCase(String name);

}