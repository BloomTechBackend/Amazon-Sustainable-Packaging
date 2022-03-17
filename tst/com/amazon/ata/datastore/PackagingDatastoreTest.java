package com.amazon.ata.datastore;

import com.amazon.ata.types.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PackagingDatastoreTest {

    FulfillmentCenter ind1 = new FulfillmentCenter("IND1");
    FulfillmentCenter abe2 = new FulfillmentCenter("ABE2");
    FulfillmentCenter yow4 = new FulfillmentCenter("YOW4");
    FulfillmentCenter iad2 = new FulfillmentCenter("IAD2");
    FulfillmentCenter pdx1 = new FulfillmentCenter("PDX1");

    Packaging package10Cm = new Box(Material.CORRUGATE,
            BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(10));

    Packaging package20Cm = new Box(Material.CORRUGATE,
            BigDecimal.valueOf(20), BigDecimal.valueOf(20), BigDecimal.valueOf(20));

    Packaging package40Cm = new Box(Material.CORRUGATE,
            BigDecimal.valueOf(40), BigDecimal.valueOf(40), BigDecimal.valueOf(40));

    Packaging package60Cm = new Box(Material.CORRUGATE,
            BigDecimal.valueOf(60), BigDecimal.valueOf(60), BigDecimal.valueOf(60));

    Packaging package2000 = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(2000));
    Packaging package1000 = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(1000));

    Packaging package5000 = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(5000));
    Packaging package10000 = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(10000));
    Packaging package6000 = new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(6000));

    FcPackagingOption ind1_10Cm = new FcPackagingOption(ind1, package10Cm);
    FcPackagingOption abe2_20Cm = new FcPackagingOption(abe2, package20Cm);
    FcPackagingOption abe2_40Cm = new FcPackagingOption(abe2, package40Cm);
    FcPackagingOption yow4_10Cm = new FcPackagingOption(yow4, package10Cm);
    FcPackagingOption yow4_20Cm = new FcPackagingOption(yow4, package20Cm);
    FcPackagingOption yow4_60Cm = new FcPackagingOption(yow4, package60Cm);
    FcPackagingOption iad2_20Cm = new FcPackagingOption(iad2, package20Cm);
    FcPackagingOption pdx1_40Cm = new FcPackagingOption(pdx1, package40Cm);
    FcPackagingOption pdx1_60Cm = new FcPackagingOption(pdx1, package60Cm);
    FcPackagingOption iad2_2000 = new FcPackagingOption(iad2, package2000);
    FcPackagingOption iad2_1000 = new FcPackagingOption(iad2, package1000);

    FcPackagingOption iad2_5000 = new FcPackagingOption(iad2, package5000);
    FcPackagingOption yow4_2000 = new FcPackagingOption(yow4, package2000);
    FcPackagingOption yow4_5000 = new FcPackagingOption(yow4, package5000);
    FcPackagingOption yow4_10000 = new FcPackagingOption(yow4, package10000);
    FcPackagingOption ind1_2000 = new FcPackagingOption(ind1, package2000);
    FcPackagingOption ind1_5000 = new FcPackagingOption(ind1, package5000);
    FcPackagingOption abe2_2000 = new FcPackagingOption(abe2, package2000);
    FcPackagingOption abe2_6000 = new FcPackagingOption(abe2, package6000);
    FcPackagingOption pdx1_5000 = new FcPackagingOption(pdx1, package5000);
    FcPackagingOption pdx1_10000 = new FcPackagingOption(pdx1, package10000);
    FcPackagingOption yow4_5000_2 = new FcPackagingOption(yow4, package5000);


    @Test
    public void getFcPackagingOptions_get_returnAllOptions() {
        // GIVEN
        PackagingDatastore packagingDatastore = new PackagingDatastore();
//        List<FcPackagingOption> expectedPackagingOptions = Arrays.asList(ind1_10Cm, abe2_20Cm, abe2_40Cm, yow4_10Cm,
//                yow4_20Cm, yow4_60Cm, iad2_20Cm, iad2_20Cm, pdx1_40Cm, pdx1_60Cm, pdx1_60Cm, iad2_2000,iad2_1000,
//                iad2_5000,yow4_2000, yow4_5000, yow4_10000, ind1_2000, ind1_5000, abe2_2000, abe2_6000, pdx1_5000,
//                pdx1_10000, yow4_5000_2);

        List<FcPackagingOption> expectedPackagingOptions = Arrays.asList(ind1_10Cm, abe2_20Cm, abe2_40Cm, yow4_10Cm,
                yow4_20Cm, yow4_60Cm, iad2_20Cm, iad2_20Cm, pdx1_40Cm, pdx1_60Cm, pdx1_60Cm, iad2_2000,iad2_1000);

        // WHEN
        List<FcPackagingOption> fcPackagingOptions = packagingDatastore.getFcPackagingOptions();

        // THEN
        assertEquals(expectedPackagingOptions.size(), fcPackagingOptions.size(),
                String.format("There should be %s FC/Packaging pairs.", expectedPackagingOptions.size()));
        for (FcPackagingOption expectedPackagingOption : expectedPackagingOptions) {
            assertTrue(fcPackagingOptions.contains(expectedPackagingOption), String.format("expected packaging " +
                            "options from PackagingDatastore to contain %s package in fc %s",
                    expectedPackagingOption.getPackaging(),
                    expectedPackagingOption.getFulfillmentCenter().getFcCode()));
        }
        assertTrue(true, "getFcPackagingOptions contained all of the expected options.");
    }
}
