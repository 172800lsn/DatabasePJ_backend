package com.example.vehiclerepairsystem.controller;

import com.example.vehiclerepairsystem.model.RepairOrder;
import com.example.vehiclerepairsystem.service.RepairOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*") // 可根据需要限制来源
@RestController
@RequestMapping("/api/repair-orders")
public class RepairOrderController {

    private final RepairOrderService repairOrderService;

    public RepairOrderController(RepairOrderService repairOrderService) {
        this.repairOrderService = repairOrderService;
    }

    @PostMapping("/report")
    public ResponseEntity<?> reportRepair(@RequestBody Map<String, String> reportRequest) {
        try {
            System.out.println("Received report request: " + reportRequest);
            System.out.println("Request User: " + reportRequest.get("requestUser"));
            //打印获取得到的数据类型
            System.out.println("Request User Type: " + reportRequest.get("requestUser").getClass());
            System.out.println("License Plate: " + reportRequest.get("licensePlate").getClass());
            System.out.println("Vehicle Model: " + reportRequest.get("vehicleModel").getClass());
            System.out.println("Description: " + reportRequest.get("description").getClass());
            // 调用 Service 保存维修订单
            RepairOrder repairOrder = repairOrderService.reportRepair(
                    reportRequest.get("requestUser"),
                    reportRequest.get("licensePlate"),
                    reportRequest.get("vehicleModel"),
                    reportRequest.get("description")
            );
            System.out.println("repairOrder'id: " + repairOrder.getId());
            return ResponseEntity.ok("报修信息已成功提交" );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("提交失败：" + e.getMessage());
        }
    }

}