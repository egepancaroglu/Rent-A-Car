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
@Table(name = "transmissions")
public class Transmission extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "transmission", fetch = FetchType.LAZY)
    private List<Model> models;
}
