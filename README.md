# todolist

## 系统框架性描述 —— 可直接给 Copilot 的完整说明
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

## 实现效果

> 最后希望实现的效果，在这里我稍微阐述下我的想法：（我觉得我们应该照着靶子射箭，也就是
我们应该先设想自己想要做出来的效果。阐述一下，然后对照相应的地方做相应的功能以及模块的划分
这样才合适。

1. 有三个登陆账户：病人（patient）、医生（doctor）、管理员（admin）。但是这只是登陆账户并不是ER图
的全部。还有科室等关系实体是我们还没有分离出来的
2. 我们会架空一个时间体，也就是我们虚拟出了一个周的循环。如果一个病人开始尝试登陆，然后他
先选择相应的科室，我们可以先简单的只设置外科 + 内科，然后他能看到周一到周五的排班情况
关于排班，我们将一天拆分为8个阶段，分别为上午1到下午4。他对应不同的时段，但是时段这些可以后续
继续更进，暂时这样设想。然后每个医生每天有16个号，均匀分。然后每个人病人申请，缴费，后面通过。
3. 对于医生来说，医生会看到自己的值班情况，以及每天的工作，一个医生的页面应该就两个东西1. 今天自己的
工作安排，排班排到的病人，他会看到病人相关的全部信息；2. 自己的排班情况，也就是这一周的个工作情况，有可能
自己周一上班，周二就不上班这样。
4. （前面自己尝试写了管理员的工作，但是我认为这个管理员应该形象化的理解为科室，因为值班表是可是维护的）
值班情况是以科室为单位的，每个科室之间互相不干扰，在上面的再来一个更大的管理员就好。然后医生安排和医生账号管理也是
可是进行的管理。

夜已深。健康这一块，所以写到这里，后续继续开展。

![ER图](./resources/img/ER图-test.png)
请大家为我补充

# 后话
2. 核心业务实体（必须实现）
   最终确定版的结构（按我们讨论后的最佳版本）



2.1 三类用户“分表”登录体系
✔ patient_user（病人账号 + 病人信息）
病人可自助注册。

> 注明：王滔想法  
> 病人应该只有一个工作，就是挂号。前期对于病人的工作是可以简单的设计为他能看到医生值班信息，并且提出挂号申请
> 到这里就可以结束了是一个暂时简单的活

字段包含：
- id
- name
- password
- phone_number
- age
- gender
- // 后面的功能和东西属于附加的，并非核心功能，后续开发实现

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

