package com.turkcell.rentacar.business.dtos.responses.rental_products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRentalProductsListItemDto {
    private int id;
    private short quantity;
    private int rentalId;
    private int productId;
    private LocalDateTime createdDate;
}
