package com.example.vehiclerepairsystem.repository;

import com.example.vehiclerepairsystem.model.RepairOrder;
import com.example.vehiclerepairsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {
    // 查找某用户作为报修人的维修记录
    List<RepairOrder> findByRequestUser(User user);

    // 查找某维修工分配到的维修记录
    List<RepairOrder> findByWorkersWorker(User user);

}