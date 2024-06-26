package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.dtos.requests.corporate_customers.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.requests.corporate_customers.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.business.dtos.responses.corporate_customers.CreatedCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.corporate_customers.GetAllCorporateCustomersListItemDto;
import com.turkcell.rentacar.business.dtos.responses.corporate_customers.GetCorporateCustomerResponse;
import com.turkcell.rentacar.business.dtos.responses.corporate_customers.UpdatedCorporateCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/corporate-customers")
public class CorporateCustomersController {
    private CorporateCustomerService corporateCustomerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCorporateCustomerResponse add(@Valid @RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        return corporateCustomerService.add(createCorporateCustomerRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCorporateCustomerResponse update(@PathVariable int id, @Valid @RequestBody UpdateCorporateCustomerRequest corporateCustomer) {
        return corporateCustomerService.update(id, corporateCustomer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        corporateCustomerService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCorporateCustomerResponse get(@PathVariable int id) {
        return corporateCustomerService.get(id);
    }

    @PatchMapping("/{id}/findeks-score")
    @ResponseStatus(HttpStatus.OK)
    public void updateFindeksScore(@PathVariable int id) {
        corporateCustomerService.updateFindeksScore(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCorporateCustomersListItemDto> getAll() {
        return corporateCustomerService.getAll();
    }
}

/*
1 - brands, fuels, transmissions, models (sadece entity) -> Mert
2 - cars (plate(repository), min_findeks_score, state (vurgular)) ve maintenance(business rules ve returnCar();) -> Özgür
3 - customers (Individual customers ve corporate customers findeksService, Adapter ve Manager) -> Ömer Ç.
4 - rental, product, rental_product ve payment tablo ilişkisi -> Ömer Faruk
5 - rental add ve returnCar(kiralanan arabayı available durumuna çekme) ->
6 - payment service komple ->
7 - rentalProduct service komple -> Rozerin
*/