package com.example.vehiclerepairsystem.repository;

import com.example.vehiclerepairsystem.model.OrderWorkerAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AssignmentRepository extends JpaRepository<OrderWorkerAssignment, Long> {

}
