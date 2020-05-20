package com.example.ecom.usertracking.service;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE, onConstructor = @__(@Autowired))
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserTrackingQueryService {
 /*   List<PurchaseOrderSummaryDto> getSaleSummaryGroupByState();
    PurchaseOrderSummaryDto getSaleSummaryByState(String state);
    double getTotalSale();
    History*/
}
