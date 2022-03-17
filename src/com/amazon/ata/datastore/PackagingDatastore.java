package com.amazon.ata.datastore;

import com.amazon.ata.types.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Stores all configured packaging pairs for all fulfillment centers.
 */
public class PackagingDatastore {

    /**
     * The stored pairs of fulfillment centers to the packaging options they support.
     */
    private final List<FcPackagingOption> fcPackagingOptions = Arrays.asList(
            createFcPackagingOption("IND1", Material.CORRUGATE, List.of(new String[]{"10", "10", "10"})),
            createFcPackagingOption("ABE2", Material.CORRUGATE, List.of(new String[]{"20", "20", "20"})),
            createFcPackagingOption("ABE2", Material.CORRUGATE, List.of(new String[]{"40", "40", "40"})),
            createFcPackagingOption("YOW4", Material.CORRUGATE, List.of(new String[]{"10", "10", "10"})),
            createFcPackagingOption("YOW4", Material.CORRUGATE, List.of(new String[]{"20", "20", "20"})),
            createFcPackagingOption("YOW4", Material.CORRUGATE, List.of(new String[]{"60", "60", "60"})),
            createFcPackagingOption("IAD2", Material.CORRUGATE, List.of(new String[]{"20", "20", "20"})),
            createFcPackagingOption("IAD2", Material.CORRUGATE, List.of(new String[]{"20", "20", "20"})),
            createFcPackagingOption("PDX1", Material.CORRUGATE, List.of(new String[]{"40", "40", "40"})),
            createFcPackagingOption("PDX1", Material.CORRUGATE, List.of(new String[]{"60", "60", "60"})),
            createFcPackagingOption("PDX1", Material.CORRUGATE, List.of(new String[]{"60", "60", "60"})),

            createFcPackagingOption("IAD2", Material.LAMINATED_PLASTIC, List.of(new String[]{"2000"})),
            createFcPackagingOption("IAD2", Material.LAMINATED_PLASTIC, List.of(new String[]{"1000"}))

//            createFcPackagingOption("IAD2", Material.LAMINATED_PLASTIC, List.of(new String[]{"5000"})),
//            createFcPackagingOption("YOW4", Material.LAMINATED_PLASTIC, List.of(new String[]{"2000"})),
//            createFcPackagingOption("YOW4", Material.LAMINATED_PLASTIC, List.of(new String[]{"5000"})),
//            createFcPackagingOption("YOW4", Material.LAMINATED_PLASTIC, List.of(new String[]{"10000"})),
//            createFcPackagingOption("IND1", Material.LAMINATED_PLASTIC, List.of(new String[]{"2000"})),
//            createFcPackagingOption("IND1", Material.LAMINATED_PLASTIC, List.of(new String[]{"5000"})),
//            createFcPackagingOption("ABE2", Material.LAMINATED_PLASTIC, List.of(new String[]{"2000"})),
//            createFcPackagingOption("ABE2", Material.LAMINATED_PLASTIC, List.of(new String[]{"6000"})),
//            createFcPackagingOption("PDX1", Material.LAMINATED_PLASTIC, List.of(new String[]{"5000"})),
//            createFcPackagingOption("PDX1", Material.LAMINATED_PLASTIC, List.of(new String[]{"10000"})),
//            createFcPackagingOption("YOW4", Material.LAMINATED_PLASTIC, List.of(new String[]{"5000"}))
    );

    /**
     * Create fulfillment center packaging option from provided parameters.
     */
    private FcPackagingOption createFcPackagingOption(String fcCode, Material material, List<String> dimensions) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);

        Packaging packaging = null;
        if (dimensions.size() == 3) {
            packaging = new Box(material, BigDecimal.valueOf(Long.parseLong(dimensions.get(0))),
                    BigDecimal.valueOf(Long.parseLong(dimensions.get(1))),
                    BigDecimal.valueOf(Long.parseLong(dimensions.get(2))));
        }

        if (dimensions.size() == 1) {
            packaging = new PolyBag(material, BigDecimal.valueOf(Long.parseLong(dimensions.get(0))));
        }
        return new FcPackagingOption(fulfillmentCenter, packaging);
    }

    public List<FcPackagingOption> getFcPackagingOptions() {
        return fcPackagingOptions;
    }
}
