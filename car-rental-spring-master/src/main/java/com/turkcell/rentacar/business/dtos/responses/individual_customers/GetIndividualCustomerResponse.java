package com.turkcell.rentacar.business.dtos.responses.individual_customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIndividualCustomerResponse {
    private int id;
    private int customerId;
    private String firstName;
    private String lastName;
    private String identityNumber;
    private LocalDateTime createdDate;
}
