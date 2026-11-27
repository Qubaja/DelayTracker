package io.github.qubaja.delaytrackerbackend.model;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal lon;

    @Column(nullable = false)
    private BigDecimal lat;

    @Column(nullable = false, unique = true)
    private String gtfsStopId;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public String getGtfsStopId() {
        return gtfsStopId;
    }
}