package com.example.vehiclerepairsystem.controller;

import lombok.Data;

@Data
public class RepairOrderRequest {
    private String requestUser;
    private String licensePlate;
    private String vehicleModel;
    private String description;
}