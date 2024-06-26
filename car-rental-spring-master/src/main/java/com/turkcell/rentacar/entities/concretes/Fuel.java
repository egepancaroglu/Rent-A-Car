package com.turkcell.rentacar.entities.concretes;

import com.turkcell.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fuels")
public class Fuel extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "fuel", fetch = FetchType.LAZY)
    private List<Model> models;
}
