package com.shinhan.sbproject.dto;


import lombok.*;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class CarDTO {
    @NonNull
    int carId;


    final String model;
    int price;
}
