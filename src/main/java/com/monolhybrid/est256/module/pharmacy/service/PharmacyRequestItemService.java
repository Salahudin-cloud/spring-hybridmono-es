package com.monolhybrid.est256.module.pharmacy.service;

import com.monolhybrid.est256.module.pharmacy.entity.PharmacyRequestItem;
import com.monolhybrid.est256.module.pharmacy.repository.PharmacyRequestItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PharmacyRequestItemService {

    private final PharmacyRequestItemRepository pharmacyRequestItemRepository;


    public List<PharmacyRequestItem> getAllItemByRequestId(Long requestId) {
        List<PharmacyRequestItem> items =  pharmacyRequestItemRepository.findByPharmacyRequestId(requestId);

        if (items.isEmpty()) {
            return Collections.emptyList();
        }
        return items;
    }

}
