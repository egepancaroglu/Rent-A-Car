package com.turkcell.rentacar.entities.concretes;

import com.turkcell.rentacar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "maintenances")
public class Maintenance extends BaseEntity {
    @Column(name = "sent_date")
    private LocalDateTime sentDate;

    @Column(name = "expected_return_date")
    private LocalDateTime expectedReturnDate;

    @Column(name = "actual_return_date")
    private LocalDateTime actualReturnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;
}