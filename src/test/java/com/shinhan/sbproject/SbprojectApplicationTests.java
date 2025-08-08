package com.shinhan.sbproject;

import com.shinhan.sbproject.dto.CarDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbprojectApplicationTests {

    @Test
    void f1() {
        CarDTO car1 = new CarDTO(1,"c1");
        CarDTO car2 = new CarDTO(2,"c2");
        CarDTO car3 = new CarDTO(3,"c3");
        CarDTO car4 = CarDTO.builder().carId(4).model("c4").price(1000).build();
        System.out.println(car1);
        System.out.println(car2);
        System.out.println(car3);
        System.out.println(car4);

    }

    @Test
    void f2() {}

}
