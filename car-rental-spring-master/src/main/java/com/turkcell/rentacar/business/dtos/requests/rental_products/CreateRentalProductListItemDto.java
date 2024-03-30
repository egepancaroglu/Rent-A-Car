package com.turkcell.rentacar.business.dtos.requests.rental_products;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRentalProductListItemDto {
    @NotNull
    @Min(0)
    private short quantity;

    @NotNull
    private int productId;
}
