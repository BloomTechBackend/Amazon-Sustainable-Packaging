package com.amazon.ata.service;

import com.amazon.ata.exceptions.NoPackagingFitsItemException;
import com.amazon.ata.exceptions.UnknownFulfillmentCenterException;
import com.amazon.ata.strategy.CostStrategy;
import com.amazon.ata.strategy.MonetaryCostStrategy;
import com.amazon.ata.dao.PackagingDAO;
import com.amazon.ata.datastore.PackagingDatastore;
import com.amazon.ata.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class ShipmentServiceTest {
//
    @InjectMocks
    ShipmentService shipmentService;

    @Mock
    PackagingDAO packagingDAO;
    @Mock
    CostStrategy costStrategy;
    @Mock
    ShipmentCost shipmentCost;

    private Item smallItem = Item.builder()
            .withHeight(BigDecimal.valueOf(1))
            .withWidth(BigDecimal.valueOf(1))
            .withLength(BigDecimal.valueOf(1))
            .withAsin("abcde")
            .build();

    private Item largeItem = Item.builder()
            .withHeight(BigDecimal.valueOf(1000))
            .withWidth(BigDecimal.valueOf(1000))
            .withLength(BigDecimal.valueOf(1000))
            .withAsin("12345")
            .build();

    private FulfillmentCenter existentFC = new FulfillmentCenter("ABE2");
    private FulfillmentCenter nonExistentFC = new FulfillmentCenter("NonExistentFC");

//    shipmentService = new ShipmentService(new PackagingDAO(new PackagingDatastore()),
//            new MonetaryCostStrategy());
//    ShipmentService shipmentService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        shipmentService = new ShipmentService(packagingDAO, costStrategy);
//        packagingDAO = new PackagingDAO(new PackagingDatastore());
    }


    @Test
    void findBestShipmentOption_existentFCAndItemCanFit_returnsShipmentOption() throws Exception {

        //GIVEN
        List<ShipmentOption> shipmentOptionList = new ArrayList<>();
        shipmentOptionList.add(ShipmentOption.builder()
                .withItem(smallItem)
//                .withPackaging(new Box(Material.CORRUGATE, BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(1)))
                .withFulfillmentCenter(existentFC)
                .build());

        when(packagingDAO.findShipmentOptions(smallItem, existentFC)).thenReturn(shipmentOptionList);
        when(costStrategy.getCost(shipmentOptionList.get(0))).thenReturn(new ShipmentCost(shipmentOptionList.get(0), BigDecimal.valueOf(1)));

        // WHEN
        ShipmentOption shipmentOption = shipmentService.findShipmentOption(smallItem, existentFC);

        // THEN
        assertNotNull(shipmentOption);
    }

    @Test
    void findBestShipmentOption_existentFCAndItemCannotFit_returnsShipmentOption() throws Exception {
        // GIVEN
        List<ShipmentOption> shipmentOptionList = new ArrayList<>();
        shipmentOptionList.add(ShipmentOption.builder()
                .withItem(largeItem)
//                .withPackaging(new Box(Material.CORRUGATE, BigDecimal.valueOf(1000), BigDecimal.valueOf(1000), BigDecimal.valueOf(1000)))
                .withFulfillmentCenter(existentFC)
                .build());


//        when(packagingDAO.findShipmentOptions(largeItem, existentFC)).thenThrow(NoPackagingFitsItemException.class);
        when(packagingDAO.findShipmentOptions(largeItem, existentFC)).thenReturn(shipmentOptionList);
        when(costStrategy.getCost(shipmentOptionList.get(0))).thenReturn(new ShipmentCost(null, BigDecimal.valueOf(1)));


        //WHEN
        ShipmentOption shipmentOption = shipmentService.findShipmentOption(largeItem, existentFC);

        // THEN
        assertNull(shipmentOption);
    }

    @Test
    void findBestShipmentOption_nonExistentFCAndItemCanFit_returnsShipmentOption() throws UnknownFulfillmentCenterException, NoPackagingFitsItemException {
        // GIVEN
        List<ShipmentOption> shipmentOptionList = new ArrayList<>();
        shipmentOptionList.add(ShipmentOption.builder()
                .withItem(smallItem)
                .withFulfillmentCenter(nonExistentFC)
                .build());

        when(packagingDAO.findShipmentOptions(smallItem, nonExistentFC)).thenReturn(shipmentOptionList);
        when(costStrategy.getCost(shipmentOptionList.get(0))).thenReturn(new ShipmentCost(null, BigDecimal.valueOf(1)));

//        //  WHEN
        ShipmentOption shipmentOption = shipmentService.findShipmentOption(smallItem, nonExistentFC);
//
        // THEN
        assertNull(shipmentOption);
    }

    @Test
    void findBestShipmentOption_nonExistentFCAndItemCannotFit_returnsShipmentOption() throws Exception {
        // GIVEN
        List<ShipmentOption> shipmentOptionList = new ArrayList<>();
        shipmentOptionList.add(ShipmentOption.builder()
                .withItem(largeItem)
                .withFulfillmentCenter(nonExistentFC)
                .build());

        when(packagingDAO.findShipmentOptions(largeItem, nonExistentFC)).thenReturn(shipmentOptionList);
        when(costStrategy.getCost(shipmentOptionList.get(0))).thenReturn(new ShipmentCost(null, BigDecimal.valueOf(1)));


        //WHEN
        ShipmentOption shipmentOption = shipmentService.findShipmentOption(largeItem, nonExistentFC);

        // THEN
        assertNull(shipmentOption);
    }
}