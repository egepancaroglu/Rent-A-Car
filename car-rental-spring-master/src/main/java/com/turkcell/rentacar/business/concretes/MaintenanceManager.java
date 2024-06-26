package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.MaintenanceService;
import com.turkcell.rentacar.business.dtos.requests.maintenances.CreateMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.requests.maintenances.ReturnCarRequest;
import com.turkcell.rentacar.business.dtos.requests.maintenances.UpdateMaintenanceRequest;
import com.turkcell.rentacar.business.dtos.responses.maintenances.CreatedMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.maintenances.GetAllMaintenancesListItemDto;
import com.turkcell.rentacar.business.dtos.responses.maintenances.GetMaintenanceResponse;
import com.turkcell.rentacar.business.dtos.responses.maintenances.UpdatedMaintenanceResponse;
import com.turkcell.rentacar.business.rules.CarBusinessRules;
import com.turkcell.rentacar.business.rules.MaintenanceBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.data_access.abstracts.MaintenanceRepository;
import com.turkcell.rentacar.entities.concretes.Maintenance;
import com.turkcell.rentacar.entities.enums.CarState;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final ModelMapperService modelMapperService;
    private final MaintenanceBusinessRules maintenanceBusinessRules;
    private final CarBusinessRules carBusinessRules;
    private final CarService carService;

    @Override
    public CreatedMaintenanceResponse add(CreateMaintenanceRequest createMaintenanceRequest) {
        carBusinessRules.carIdShouldBeExist(createMaintenanceRequest.getCarId());
        carBusinessRules.carShouldNotBeInMaintenance(createMaintenanceRequest.getCarId());
        carBusinessRules.carShouldNotBeRented(createMaintenanceRequest.getCarId());
        Maintenance maintenance = modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);

        carService.updateState(createMaintenanceRequest.getCarId(), CarState.MAINTENANCE);

        Maintenance createdMaintenance = maintenanceRepository.save(maintenance);
        return modelMapperService.forResponse().map(createdMaintenance, CreatedMaintenanceResponse.class);
    }

    @Override
    public UpdatedMaintenanceResponse update(int id, UpdateMaintenanceRequest updateMaintenanceRequest) {
        maintenanceBusinessRules.maintenanceIdShouldBeExist(id);
        Maintenance maintenanceToUpdate = modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
        maintenanceToUpdate.setId(id);
        Maintenance updatedMaintenance = maintenanceRepository.save(maintenanceToUpdate);
        return modelMapperService.forResponse().map(updatedMaintenance, UpdatedMaintenanceResponse.class);
    }

    @Override
    public void delete(int id) {
        Optional<Maintenance> foundOptionalMaintenance = maintenanceRepository.findById(id);
        maintenanceBusinessRules.maintenanceShouldBeExist(foundOptionalMaintenance);
        maintenanceRepository.delete(foundOptionalMaintenance.get());
    }

    @Override
    public List<GetAllMaintenancesListItemDto> getAll() {
        List<Maintenance> maintenances = maintenanceRepository.findAll();
        return modelMapperService.forResponse().map(maintenances, new TypeToken<List<GetAllMaintenancesListItemDto>>() {
        }.getType());
    }

    @Override
    public GetMaintenanceResponse get(int id) {
        Optional<Maintenance> optionalMaintenance = maintenanceRepository.findById(id);
        maintenanceBusinessRules.maintenanceShouldBeExist(optionalMaintenance);
        return modelMapperService.forResponse().map(optionalMaintenance.get(), GetMaintenanceResponse.class);
    }

    @Override
    public void returnCar(ReturnCarRequest returnCarRequest) {
        Optional<Maintenance> optionalMaintenance = maintenanceRepository.findById(returnCarRequest.getMaintenanceId());
        maintenanceBusinessRules.maintenanceShouldBeExist(optionalMaintenance);
        Maintenance maintenance = optionalMaintenance.get();
        maintenanceBusinessRules.maintenanceShouldNotBeReturned(maintenance);

        maintenance.setActualReturnDate(returnCarRequest.getActualReturnDate());
        carService.updateState(maintenance.getCar().getId(), CarState.AVAILABLE);

        maintenanceRepository.save(maintenance);
    }
}
