package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.corporate_customers.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.corporate_customers.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.corporate_customers.CreatedCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.corporate_customers.GetAllCorporateCustomersListItemDto;
import com.turkcell.rentacar.business.dtos.responses.corporate_customers.GetCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.corporate_customers.UpdatedCorporateCustomerResponse;

import java.util.List;

public interface CorporateCustomerService {
    CreatedCorporateCustomerResponse add(CreateCorporateCustomerRequest createCorporateCustomerRequest);

    UpdatedCorporateCustomerResponse update(int id, UpdateCorporateCustomerRequest updateCorporateCustomerRequest);

    void delete(int id);

    List<GetAllCorporateCustomersListItemDto> getAll();

    GetCorporateCustomerResponse get(int id);

    void updateFindeksScore(int id);
}
