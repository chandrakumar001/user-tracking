package com.example.ecom.hardware;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

public class UniqueIdentifierValidator {


    private static final String ERROR_CHANGE_CATEGORY_ID_EMPTY = "changeCategory.id.empty";
    private static final String ERROR_CHANGE_CATEGORY_ID_INVALID = "changeCategory.id.invalid";

    private static final String ERROR_PARTCV_ID_EMPTY = "partcv.id.empty";
    private static final String ERROR_PARTCV_ID_INVALID = "partcv.id.invalid";

    private static final String ERROR_PCID_AND_DSID_EMPTY = "partcv.dsid.id.empty";
    private static final String ERROR_PCID_AND_DSID_AND_IID_INVALID = "partcv.dsid.iid.id.invalid";
    private static final String ERROR_PCID_AND_DSID_AND_IID_EMPTY = "partcv.dsid.iid.id.empty";
    private static final String ERROR_PCID_AND_DSID_INVALID = "partcv.dsid.id.invalid";
    private static final String ERROR_DSTATUS_ID_EMPTY = "dstatus.id.empty";
    private static final String ERROR_DSTATUS_ID_INVALID = "dstatus.id.invalid";
    private static final String ERROR_ISSUE_ID_EMPTY = "issue.id.empty";
    private static final String ERROR_ISSUE_ID_INVALID = "issue.id.invalid";
    private static final String ERROR_SUPPLIER_ID_EMPTY = "supplier.id.empty";
    private static final String ERROR_SUPPLIER_ID_INVALID = "supplier.id.invalid";
    private static final String ERROR_SUPPLIER_LOCATION_ID_EMPTY = "supplier.location.id.empty";
    private static final String ERROR_SUPPLIER_LOCATION_ID_INVALID = "supplier.location.invalid";
    private static final String ERROR_SUPPLIER_AND_SUPPLIER_LOCATION_ID = "supplier.supplier.location.id.invalid";
    private static final String ERROR_DAIMLER_PLANT_LOCATION_ID_EMPTY = "daimler.plant.location.id.empty";
    private static final String ERROR_DAIMLER_PLANT_LOCATION_ID_INVALID = "daimler.plant.location.invalid";
    private static final String ERROR_VSTO_CONTACT_ID_EMPTY = "vsto.contact.id.empty";
    private static final String ERROR_VSTO_CONTACT_ID_INVALID = "vsto.contact.id.invalid";
    private static final String ERROR_PSTO_CONTACT_ID_EMPTY = "psto.contact.id.empty";
    private static final String ERROR_PSTO_CONTACT_ID_INVALID = "psto.contact.id.invalid";


    public static Optional<String> validateHid(final String pcid) {

        return validateUUIDFormat(
                pcid,
                ERROR_PARTCV_ID_EMPTY,
                ERROR_PARTCV_ID_INVALID
        );
    }

    public static Optional<String> validateUUIDFormat(@NotNull final String id,
                                                      @NotNull final String errorMessage,
                                                      @NotNull final String formatErrorMessage) {

        if (org.springframework.util.StringUtils.isEmpty(id)) {
            return Optional.of(errorMessage);
        }

        try {
            UUID.fromString(id);
            return Optional.empty();
        } catch (final IllegalArgumentException illegalArgumentException) {
            return Optional.of(formatErrorMessage);
        }
    }
}
