package com.example.vehiclerepairsystem.controller;

import com.example.vehiclerepairsystem.model.Payment;
import com.example.vehiclerepairsystem.model.RepairOrder;
import com.example.vehiclerepairsystem.model.User;
import com.example.vehiclerepairsystem.model.Vehicle;
import com.example.vehiclerepairsystem.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*") // 可根据需要限制来源
@RestController
@RequestMapping("/api/repair-orders")
public class RepairOrderController {

    private final RepairOrderService repairOrderService;
    private final RepairService repairService;

    public RepairOrderController(RepairOrderService repairOrderService, RepairService repairService) {
        this.repairOrderService = repairOrderService;
        this.repairService = repairService;
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

    // 新增获取维修记录的接口
    @PostMapping("/repairs")
    public ResponseEntity<?> getRepairs(@RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            System.out.println("username: " + username);
            // 获取维修记录
            List<Map<String, Object>> repairs = repairOrderService.getRepairsByUsername(username);
            return ResponseEntity.ok(Map.of("repairs", repairs)); // 响应符合前端结构
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("查询失败：" + e.getMessage());
        }
    }

    @PostMapping("/submit-feedback")
    public ResponseEntity<?> submitFeedback(@RequestBody Map<String, Object> feedbackRequest) {
        try {
            // 提取前端传来的数据
            Long orderId = Long.valueOf(feedbackRequest.get("orderId").toString());
            Boolean isUrgent = (Boolean) feedbackRequest.get("isUrgent");
            String feedback = (String) feedbackRequest.get("feedback");
            Double score = feedbackRequest.get("score") != null ? Double.valueOf(feedbackRequest.get("score").toString()) : null;

            // 调用服务层方法进行处理
            repairOrderService.submitFeedback(orderId, isUrgent, feedback, score);

            return ResponseEntity.ok(Map.of("message", "反馈提交成功！"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of("message", "提交反馈失败：" + e.getMessage()));
        }
    }
    @PostMapping("/worker/tasks")
    public ResponseEntity<?> getFeedback(@RequestBody Map<String, String> request) {
        try {
            String workerName = request.get("username");
            List<RepairOrder> repairOrders = repairService.findRepairOrdersByWorkerNameAndStatus(workerName, RepairOrder.Status.IN_PROGRESS);
            List<Map<String, Object>> formattedTasks = repairOrders.stream().map(order -> {
                Map<String, Object> task = new HashMap<>();
                task.put("id", order.getId());
                task.put("description", order.getDescription());
                Vehicle vehicle = order.getVehicle();
                String vehicleInfo = vehicle.getVehicleInfo();
                task.put("vehicleInfo", vehicleInfo);
               task.put("isUrgent", order.getIsUrgent());
                return task;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(Map.of("tasks", formattedTasks));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of("message", "获取工单失败" + e.getMessage()));
        }
    }
    @PostMapping("/worker/pending-tasks")
    public ResponseEntity<?> getPendFeedback(@RequestBody Map<String, String> request) {
        try {
            String workerName = request.get("username");
            List<RepairOrder> repairOrders = repairService.findRepairOrdersByWorkerNameAndStatus(workerName, RepairOrder.Status.TO_ACCEPT);
            List<Map<String, Object>> formattedTasks = repairOrders.stream().map(order -> {
                Map<String, Object> task = new HashMap<>();
                task.put("id", order.getId());
                task.put("description", order.getDescription());
                Vehicle vehicle = order.getVehicle();
                String vehicleInfo = vehicle.getVehicleInfo();
                task.put("vehicleInfo", vehicleInfo);
                return task;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(Map.of("tasks", formattedTasks));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of("message", "获取工单失败" + e.getMessage()));
        }
    }
    @PostMapping("/worker/history-tasks")
    public ResponseEntity<?> getHisFeedback(@RequestBody Map<String, String> request) {
        try {
            String workerName = request.get("username");
            List<RepairOrder> repairOrders = repairService.findRepairOrdersByWorkerNameAndStatus(workerName, RepairOrder.Status.COMPLETED);
            List<Map<String, Object>> formattedTasks = repairOrders.stream().map(order -> {
                Map<String, Object> task = new HashMap<>();
                task.put("id", order.getId());
                task.put("description", order.getDescription());
                Vehicle vehicle = order.getVehicle();
                Payment payment = repairService.getPaymentByRepairOrderId(order.getId());
                String vehicleInfo = vehicle.getVehicleInfo();
                task.put("vehicleInfo", vehicleInfo);
                task.put("feedback", order.getFeedback());
                task.put("score", order.getScore());
                task.put("salary", payment!= null? payment.getAmount().doubleValue() : 0);
                return task;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(Map.of("tasks", formattedTasks));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of("message", "获取工单失败" + e.getMessage()));
        }
    }
    @GetMapping("/{task-id}")
    public ResponseEntity<?> getTask(@PathVariable("task-id") String taskId) {
        try {
            Long orderId = Long.valueOf(taskId);
            RepairOrder repairOrder = repairService.findRepairOrderById(orderId);
            Map<String, Object> response = new HashMap<>();
            response.put("description", repairOrder.getDescription());
            response.put("isUrgent", repairOrder.getIsUrgent());
            User requestUser = repairOrder.getRequestUser();
            if(requestUser != null&& requestUser.getEmail() != null) {
                response.put("requestUserEmail", requestUser.getEmail());
                System.out.println("requestUserEmail: " + requestUser.getEmail());
            }

            // 获取车辆信息
            Vehicle vehicle = repairOrder.getVehicle();
            response.put("vehicleInfo", vehicle != null ? vehicle.getVehicleInfo() : "");


            // 处理材料信息
            List<Map<String, Object>> materials = Optional.ofNullable(repairOrder.getMaterials())
                    .orElse(List.of())
                    .stream()
                    .map(material -> {
                        Map<String, Object> matMap = new HashMap<>();
                        matMap.put("name", material.getName());
                        matMap.put("quantity", material.getQuantity());
                        matMap.put("price", material.getPrice());
                        return matMap;
                    }).collect(Collectors.toList());

            response.put("materials", materials);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of("message", "获取工单失败" + e.getMessage()));
        }


    }
    @PostMapping("/accept")
    public void acceptTask(@RequestBody Map<String, Object> request) {
        try {
            Long taskId = Long.valueOf(request.get("taskId").toString());
            String workerName = (String) request.get("worker");

            // 调用服务层处理工单接受逻辑
            repairService.acceptTask(taskId, workerName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/reject")
    public void rejectTask(@RequestBody Map<String, Object> request) {
        try {
            Long taskId = Long.valueOf(request.get("taskId").toString());
            String workerName = (String) request.get("worker");
            // 调用服务层处理工单接受逻辑
            repairService.rejectTask(taskId, workerName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PutMapping("/{task-id}/complete")
    public void completeTask(@PathVariable("task-id") String taskid, @RequestBody Map<String, Object> requestData) {
        try {
            Long taskId = Long.valueOf(taskid);
            Number workTimeData = (Number) requestData.get("hours");
            Double workTime = workTimeData.doubleValue();
            repairService.completeTask(taskId, workTime);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PutMapping("/{task-id}/materials")
    public void updateMaterials(@PathVariable("task-id") String taskid, @RequestBody Map<String, Object> request) {
        try {
            Long taskId = Long.valueOf(taskid);
            List<Map<String, Object>> materialsList = (List<Map<String, Object>>) request.get("materials");
            repairService.updateMaterials(taskId, materialsList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}