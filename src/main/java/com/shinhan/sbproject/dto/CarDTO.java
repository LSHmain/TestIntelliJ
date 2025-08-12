package com.shinhan.sbproject.dto;


import lombok.*;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class CarDTO {
    @NonNull
    Long carId;

    //
    final String model;
    int price;
}
