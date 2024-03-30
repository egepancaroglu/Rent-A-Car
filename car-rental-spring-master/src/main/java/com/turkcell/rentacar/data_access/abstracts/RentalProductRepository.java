package com.turkcell.rentacar.data_access.abstracts;

import com.turkcell.rentacar.entities.concretes.RentalProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalProductRepository extends JpaRepository<RentalProduct, Integer> {
    List<RentalProduct> findByRentalId(int rentalId);
}
