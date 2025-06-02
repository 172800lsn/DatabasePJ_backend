## 环境配置
* Project:选择 Maven(或 Gradle ，根据需求)
* Language: 选择 Java 。
* Spring Boot Version: 选择稳定版本(如 3.5.0)
* Project Metadata:
  Group: com.example
  Artifact: vehicle-repair-system
  Name: vehicle-repair-system
  Package Name:com.example.vehiclerepairsystem
* Dependencies(必选依赖)
  Spring Web(用于构建 REST API)
  Spring Data JPA(用于数据库操作)。
  MySQL Driver(用于连接 MySQL 数据库)
  Spring Security(用于处理安全性/登录)
  Lombok(减少样板代码)
* settings.xml(配置本地仓库)
  C:/Users/用户名/.m2/settings.xml
测试账号：
  (1)
* username：charming
* password：12345678
* role: USER
  (2)
* username Dong
* password: 12345678
* role: WORKER
  (3)
* username: ctmd
* password: 12345678
* role: ADMIN

数据库关系示意图：
[users]
id (PK)
username
password
role
|
[vehicles]  <-- owner_id
id (PK)
license_plate
|
[repair_orders]  <-- vehicle_id, workers (through middle table)
id (PK)
description
|
[repair_order_worker_assignment] <-- 中间表
id (PK)
|
[repair_order_items]  <-- repair_order_id, service_item_id
id (PK)
quantity
|
[service_items]
id (PK)
name
|
[payment]  <-- repair_order_id
id (PK)
amount

推送本地更改的方法：
git status
git add .
git commit -m "提交信息"
git push <远程仓库名> <本地分支名>:<远程分支名> or   git push origin <远程分支名>

传输数据后记得修改security配置文件，允许相应的接口传输数据

1. **`mappedBy = "repairOrder"` **
  - 确定 `Material` 是被维护方，且通过 `repairOrder` 字段与 `RepairOrder` 管理关系。
  - 数据库中 `Material` 表有 `repair_order_id`，而 `RepairOrder` 表不需要新增外键字段。

2. **`cascade = CascadeType.ALL` **
  - 任何对 `RepairOrder` 的操作（新增、更新、删除等）都会联动对 `Material` 的操作。

3. **`orphanRemoval = true` **
  - 如果从 `RepairOrder` 的 `materials` 列表中删除了某个材料，则这个材料将从数据库中删除，而不是仅解除关联。
