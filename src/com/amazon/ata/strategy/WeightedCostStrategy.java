package com.amazon.ata.strategy;

import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;

public class WeightedCostStrategy implements CostStrategy {
    private MonetaryCostStrategy monetaryCostStrategy;
    private CarbonCostStrategy carbonCostStrategy;

    /**
     * Constructor.
     * @param monetaryCostStrategy a monetaryCostStrategy object.
     * @param carbonCostStrategy a carbonCostStrategy object.
     */
    public WeightedCostStrategy(MonetaryCostStrategy monetaryCostStrategy, CarbonCostStrategy carbonCostStrategy) {
        this.carbonCostStrategy = carbonCostStrategy;
        this.monetaryCostStrategy = monetaryCostStrategy;
    }

    /**
     * returns cost.
     * @param shipmentOption a shipment option with packaging
     * @return a ShipmentCost object.
     */

    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        BigDecimal weightedMonetaryCost = monetaryCostStrategy.getCost(shipmentOption)
                        .getCost().multiply(BigDecimal.valueOf(0.8));
        BigDecimal weightedCarbonCost = carbonCostStrategy.getCost(shipmentOption)
                .getCost().multiply(BigDecimal.valueOf(0.2));

        BigDecimal cost = weightedCarbonCost.add(weightedMonetaryCost);

        return new ShipmentCost(shipmentOption, cost);
    }
}
