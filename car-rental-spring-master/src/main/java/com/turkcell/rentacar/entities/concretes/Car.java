package com.turkcell.rentacar.entities.concretes;

import com.turkcell.rentacar.core.entities.BaseEntity;
import com.turkcell.rentacar.entities.enums.CarState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column(name = "model_year")
    private int modelYear;

    @Column(name = "plate")
    private String plate;

    @Column(name = "state")
    private CarState state;

    @Column(name = "daily_price")
    private double dailyPrice;

    @Column(name = "min_findeks_score")
    private int minFindeksScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<Maintenance> maintenances;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<Rental> rentals;
}