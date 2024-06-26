package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.constants.messages.CarMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.data_access.abstracts.CarRepository;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.enums.CarState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository carRepository;

    public void carShouldBeExist(Optional<Car> car) {
        if (car.isEmpty()) {
            throw new BusinessException(CarMessages.CAR_NOT_FOUND);
        }
    }

    public void carIdShouldBeExist(int carId) {
        Optional<Car> car = carRepository.findById(carId);
        if (car.isEmpty()) {
            throw new BusinessException(CarMessages.CAR_NOT_FOUND);
        }
    }

    public void carShouldNotBeInMaintenance(int carId) {
        Optional<Car> car = carRepository.findById(carId);
        if (car.get().getState().equals(CarState.MAINTENANCE)) {
            throw new BusinessException(CarMessages.CAR_UNDER_MAINTENANCE);
        }
    }

    public void carShouldNotBeRented(int carId) {
        Optional<Car> car = carRepository.findById(carId);
        if (car.get().getState().equals(CarState.RENTED)) {
            throw new BusinessException(CarMessages.CAR_RENTED);
        }
    }

    public void plateShouldBeUnique(String plate) {
        Optional<Car> car = carRepository.findByPlate(plate);
        if (car.isPresent()) {
            throw new BusinessException(CarMessages.PLATE_ALREADY_EXISTS);
        }
    }
}
