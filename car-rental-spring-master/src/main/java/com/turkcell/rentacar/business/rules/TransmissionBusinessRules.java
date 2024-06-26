package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.constants.messages.TransmissionMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.data_access.abstracts.TransmissionRepository;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TransmissionBusinessRules {
    private final TransmissionRepository transmissionRepository;

    public void transmissionShouldBeExist(Optional<Transmission> transmission) {
        if (transmission.isEmpty()) {
            throw new BusinessException(TransmissionMessages.TRANSMISSION_NOT_FOUND);
        }
    }

    public void transmissionIdShouldBeExist(int transmissionId) {
        Optional<Transmission> transmission = transmissionRepository.findById(transmissionId);
        if (transmission.isEmpty()) {
            throw new BusinessException(TransmissionMessages.TRANSMISSION_NOT_FOUND);
        }
    }

    public void transmissionNameCanNotBeDuplicatedWhenInserted(String name) {
        Optional<Transmission> foundOptionalTransmission = transmissionRepository.findByNameIgnoreCase(name.trim());
        if (foundOptionalTransmission.isPresent()) {
            throw new BusinessException(TransmissionMessages.TRANSMISSION_ALREADY_EXISTS);
        }
    }

    public void transmissionNameCanNotBeDuplicatedWhenUpdated(int id, String name) {
        boolean exists = transmissionRepository.existsByNameIgnoreCaseAndIdIsNot(name.trim(), id);
        if (exists) {
            throw new BusinessException(TransmissionMessages.TRANSMISSION_ALREADY_EXISTS);
        }
    }
}
