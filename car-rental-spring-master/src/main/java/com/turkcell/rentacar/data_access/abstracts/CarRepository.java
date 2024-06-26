package com.turkcell.rentacar.data_access.abstracts;

import com.turkcell.rentacar.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByPlate(String plate);
}
