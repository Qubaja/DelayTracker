package io.github.qubaja.delaytrackerbackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControl {


    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World";
    }

}
