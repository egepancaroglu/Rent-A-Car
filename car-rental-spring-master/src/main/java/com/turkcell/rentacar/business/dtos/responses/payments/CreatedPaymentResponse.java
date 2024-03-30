package com.turkcell.rentacar.business.dtos.responses.payments;

import com.turkcell.rentacar.entities.enums.PaymentState;
import lombok.Data;

@Data
public class CreatedPaymentResponse {
    private int id;
    private PaymentState state;
}
