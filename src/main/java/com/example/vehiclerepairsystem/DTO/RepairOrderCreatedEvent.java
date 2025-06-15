package com.example.vehiclerepairsystem.DTO;

import com.example.vehiclerepairsystem.model.RepairOrder;
public record RepairOrderCreatedEvent(
        Object source,
        RepairOrder repairOrder
) {

}
