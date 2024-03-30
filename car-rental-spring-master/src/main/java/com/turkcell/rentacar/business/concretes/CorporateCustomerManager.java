package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.adapters.findeks.FindeksScoreService;
import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.requests.corporate_customers.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.corporate_customers.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.customers.CreateCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.corporate_customers.CreatedCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.corporate_customers.GetAllCorporateCustomersListItemDto;
import com.turkcell.rentacar.business.dtos.responses.corporate_customers.GetCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.corporate_customers.UpdatedCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.customers.CreatedCustomerResponse;
import com.turkcell.rentacar.business.rules.CorporateCustomerBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.data_access.abstracts.CorporateCustomerRepository;
import com.turkcell.rentacar.entities.concretes.CorporateCustomer;
import com.turkcell.rentacar.entities.concretes.Customer;
import com.turkcell.rentacar.entities.enums.CustomerType;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CorporateCustomerManager implements CorporateCustomerService {
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final ModelMapperService modelMapperService;
    private final CorporateCustomerBusinessRules corporateCustomerBusinessRules;
    private final CustomerService customerService;
    private final FindeksScoreService findeksScoreService;

    @Override
    public CreatedCorporateCustomerResponse add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        CreatedCustomerResponse createdCustomerResponse = customerService.add(new CreateCustomerRequest(CustomerType.CORPORATE));
        Customer customer = modelMapperService.forResponse().map(createdCustomerResponse, Customer.class);

        CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
        corporateCustomer.setCustomer(customer);

        CorporateCustomer createdCorporateCustomer = corporateCustomerRepository.save(corporateCustomer);
        return modelMapperService.forResponse().map(createdCorporateCustomer, CreatedCorporateCustomerResponse.class);
    }

    @Override
    public UpdatedCorporateCustomerResponse update(int id, UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
        corporateCustomerBusinessRules.corporateCustomerIdShouldBeExist(id);
        CorporateCustomer corporateCustomerToUpdate = modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);
        corporateCustomerToUpdate.setId(id);
        CorporateCustomer updatedCorporateCustomer = corporateCustomerRepository.save(corporateCustomerToUpdate);
        return modelMapperService.forResponse().map(updatedCorporateCustomer, UpdatedCorporateCustomerResponse.class);
    }

    @Override
    public void delete(int id) {
        Optional<CorporateCustomer> foundOptionalCorporateCustomer = corporateCustomerRepository.findById(id);
        corporateCustomerBusinessRules.corporateCustomerShouldBeExist(foundOptionalCorporateCustomer);
        corporateCustomerRepository.delete(foundOptionalCorporateCustomer.get());
    }

    @Override
    public List<GetAllCorporateCustomersListItemDto> getAll() {
        List<CorporateCustomer> corporateCustomers = corporateCustomerRepository.findAll();
        return modelMapperService.forResponse().map(corporateCustomers, new TypeToken<List<GetAllCorporateCustomersListItemDto>>() {
        }.getType());
    }

    @Override
    public GetCorporateCustomerResponse get(int id) {
        Optional<CorporateCustomer> foundOptionalCorporateCustomer = corporateCustomerRepository.findById(id);
        corporateCustomerBusinessRules.corporateCustomerShouldBeExist(foundOptionalCorporateCustomer);
        return modelMapperService.forResponse().map(foundOptionalCorporateCustomer.get(), GetCorporateCustomerResponse.class);
    }

    @Override
    public void updateFindeksScore(int id) {
        Customer customer = customerService.getRecord(id);
        int newScore = findeksScoreService.getScoreForCorporate(customer.getCorporateCustomer().getTaxNo());
        customer.setFindeksScore(newScore);
        customerService.updateRecord(customer);
    }
}
