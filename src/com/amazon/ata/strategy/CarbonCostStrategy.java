package com.amazon.ata.strategy;

import com.amazon.ata.types.Material;
import com.amazon.ata.types.Packaging;
import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CarbonCostStrategy implements CostStrategy {

    private final Map<Material, BigDecimal> sustainabilityIndex;

    /**
     * Constructor.
     */

    public CarbonCostStrategy() {
        sustainabilityIndex = new HashMap<>();
        sustainabilityIndex.put(Material.CORRUGATE, BigDecimal.valueOf(0.017));
        sustainabilityIndex.put(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(0.012));
    }
    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        Packaging packaging = shipmentOption.getPackaging();
        BigDecimal cost = this.sustainabilityIndex.get(packaging.getMaterial())
                .multiply(packaging.getMass());

        return new ShipmentCost(shipmentOption, cost);
    }
}
