package com.example.vehiclerepairsystem.repository;


import com.example.vehiclerepairsystem.model.Payment;
import com.example.vehiclerepairsystem.model.RepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByRepairOrder(RepairOrder repairOrder);
}
