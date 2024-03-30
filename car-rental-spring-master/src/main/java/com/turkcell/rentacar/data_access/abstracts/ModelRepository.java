package com.turkcell.rentacar.data_access.abstracts;

import com.turkcell.rentacar.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {
}
