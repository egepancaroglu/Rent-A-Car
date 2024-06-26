package com.turkcell.rentacar.adapters.findeks;

import com.turkcell.rentacar.out_services.FindeksScoreClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindeksScoreAdapter implements FindeksScoreService {
    FindeksScoreClient findeksScoreClient;

    @Override
    public int getScoreForIndividual(String identityNumber) {
        return findeksScoreClient.getScoreForIndividual(identityNumber);
    }

    @Override
    public int getScoreForCorporate(String taxNo) {
        return findeksScoreClient.getScoreForCorporate(taxNo);
    }
}
