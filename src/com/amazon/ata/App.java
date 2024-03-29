package com.amazon.ata;


import com.amazon.ata.dao.PackagingDAO;
import com.amazon.ata.datastore.PackagingDatastore;
import com.amazon.ata.service.ShipmentService;
import com.amazon.ata.strategy.CarbonCostStrategy;
import com.amazon.ata.strategy.CostStrategy;
import com.amazon.ata.strategy.MonetaryCostStrategy;
import com.amazon.ata.strategy.WeightedCostStrategy;

public class App {
    /* don't instantiate me */
    private App() {}

    private static PackagingDatastore getPackagingDatastore() {
        return new PackagingDatastore();
    }

    private static PackagingDAO getPackagingDAO() {
        return new PackagingDAO(getPackagingDatastore());
    }

    private static CostStrategy getCostStrategy() {
        return new WeightedCostStrategy(new MonetaryCostStrategy(), new CarbonCostStrategy());
    }

    public static ShipmentService getShipmentService() {
        return new ShipmentService(getPackagingDAO(), getCostStrategy());
    }
}
