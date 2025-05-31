-- 工单-维修工关联表（用于记录工单指派情况）
CREATE TABLE assignments (
assignment_id SERIAL PRIMARY KEY,
order_id INT REFERENCES repair_orders(order_id) ON DELETE CASCADE,
repairer_id INT REFERENCES users(user_id),
accepted BOOLEAN DEFAULT FALSE,
assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- 用户反馈表
CREATE TABLE feedbacks (
feedback_id SERIAL PRIMARY KEY,
order_id INT REFERENCES repair_orders(order_id) ON DELETE SET NULL,
user_id INT REFERENCES users(user_id),
rating INT CHECK (rating BETWEEN 1 AND 5),
comment TEXT,
feedback_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- 材料记录表
CREATE TABLE repair_materials (
material_id SERIAL PRIMARY KEY,
order_id INT REFERENCES repair_orders(order_id) ON DELETE CASCADE,
name VARCHAR(100),
quantity INT,
unit_price NUMERIC(6,2),
used_by INT REFERENCES users(user_id)  -- 哪个维修工记录的
);
-- 用户表：role字段区分三种用户（user, repairer, admin）
CREATE TABLE users (
user_id SERIAL PRIMARY KEY,
username VARCHAR(50) UNIQUE NOT NULL,
password VARCHAR(100) NOT NULL,
name VARCHAR(100),
phone VARCHAR(20),
email VARCHAR(100),
role VARCHAR(20) CHECK (role IN ('user', 'repairer', 'admin')) NOT NULL,
work_type VARCHAR(50),       -- 若为维修工：工种，如机修、电工等
hourly_rate NUMERIC(6,2)     -- 若为维修工：时薪
);
-- 车辆表
CREATE TABLE vehicles (
vehicle_id SERIAL PRIMARY KEY,
owner_id INT REFERENCES users(user_id) ON DELETE CASCADE,
license_plate VARCHAR(20) UNIQUE NOT NULL,
model VARCHAR(50),
brand VARCHAR(50),
year INT
);
-- 工时记录（用于统计维修工收入）
CREATE TABLE work_logs (
log_id SERIAL PRIMARY KEY,
order_id INT REFERENCES repair_orders(order_id),
repairer_id INT REFERENCES users(user_id),
hours NUMERIC(5,2),
calculated_fee NUMERIC(8,2),
log_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- 维修工单表
CREATE TABLE repair_orders (
order_id SERIAL PRIMARY KEY,
user_id INT REFERENCES users(user_id),
vehicle_id INT REFERENCES vehicles(vehicle_id),
status VARCHAR(20) CHECK (status IN ('pending', 'in_progress', 'completed', 'cancelled')) DEFAULT 'pending',
description TEXT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
completed_at TIMESTAMP,
total_labor_cost NUMERIC(8,2) DEFAULT 0,
total_material_cost NUMERIC(8,2) DEFAULT 0
);