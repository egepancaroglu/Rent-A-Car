package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.individual_customers.CreateIndividualCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.individual_customers.UpdateIndividualCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.individual_customers.CreatedIndividualCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.individual_customers.GetAllIndividualCustomersListItemDto;
import com.turkcell.rentacar.business.dtos.responses.individual_customers.GetIndividualCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.individual_customers.UpdatedIndividualCustomerResponse;

import java.util.List;

public interface IndividualCustomerService {
    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest);

    UpdatedIndividualCustomerResponse update(int id, UpdateIndividualCustomerRequest updateIndividualCustomerRequest);

    void delete(int id);

    List<GetAllIndividualCustomersListItemDto> getAll();

    GetIndividualCustomerResponse get(int id);

    void updateFindeksScore(int id);
}
