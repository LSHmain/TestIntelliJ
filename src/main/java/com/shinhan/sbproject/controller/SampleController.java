package com.shinhan.sbproject.controller;

import com.shinhan.sbproject.dto.CarDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SampleController {
    @GetMapping("/carlist")
    public CarDTO car() {
        List<CarDTO> cars = new ArrayList<>();
        CarDTO car1 = new CarDTO(1,"car1");
        CarDTO car2 = new CarDTO(2,"c2");
        CarDTO car3 = new CarDTO(3,"c3");
        CarDTO car4 = CarDTO.builder().carId(4).model("c4").price(1000).build();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        return car1;
    }
}
