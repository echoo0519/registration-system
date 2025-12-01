# todolist

✅（一）系统框架性描述 —— 可直接给 Copilot 的完整说明
1. 技术栈（必须一致）
   后端：

- Spring Boot 4.0.0

- Spring MVC

- Spring Data JPA

- Spring Security（按三类用户分端登录）

- PostgreSQL

前端：

- Vue3（Composition API）

- Vite

- ElementPlus

- Vue Router

- Pinia

- Axios

架构模式：

- 前后端分离

- RESTful API

三端登录（patient / doctor / admin）

数据库严格分角色结构

2. 核心业务实体（必须实现）
   最终确定版的结构（按我们讨论后的最佳版本）

2.1 三类用户“分表”登录体系
✔ patient_user（病人账号 + 病人信息）
病人可自助注册。

> 注明：王滔想法  
> 病人应该只有一个工作，就是挂号。前期对于病人的工作是可以

字段包含：

id

id_card

username

password

name

gender

age

phone

address

✔ doctor_user（医生账号 + 医生信息）
医生账号由管理员创建，不可自注册。
医生可能也是病人，但通过注册 patient_user 达成。

字段包含：

id

username

password

doctor_id_card

name

title

phone

description

✔ admin_user（管理员账号）
管理员账号必须后台创建，不可自注册。

字段包含：

id

username

password

name

2.2 科室（疾病种类）
disease_type

id

name

code

description

2.3 医生与科室的多对多关系
doctor_disease_type

id

doctor_user_id

disease_type_id

2.4 挂号记录
registration

id

patient_user_id

doctor_user_id

disease_type_id

registration_time

status (PENDING/SUCCESS/CANCELLED/FINISHED)

