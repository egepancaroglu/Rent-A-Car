package com.turkcell.rentacar.entities.concretes;

import com.turkcell.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "daily_price")
    private double dailyPrice;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<RentalProduct> rentals;
}
