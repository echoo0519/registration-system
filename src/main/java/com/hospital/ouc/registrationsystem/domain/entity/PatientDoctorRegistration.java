package com.hospital.ouc.registrationsystem.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 挂号记录实体，对应数据库表 patient_doctor_registration。
 * 记录患者在指定科室与医生的某一时段的挂号信息及状态。
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "patient_doctor_registration")
public class PatientDoctorRegistration {
    /**
     * 主键ID（自增）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 患者档案（非空），多对一关联 patient_profile
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_profile_id")
    private PatientProfile patientProfile;

    /**
     * 医生档案（非空），多对一关联 doctor_profile
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_profile_id")
    private DoctorProfile doctorProfile;

    /**
     * 科室（非空），多对一关联 department
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id")
    private Department department;

    /**
     * 星期（非空），范围 1-5；由数据库 CHECK 约束保证
     */
    @Column(nullable = false)
    private Integer weekday;

    /**
     * 时间槽（非空），数据库为 time_slot，这里以字符串映射（AM1..AM4、PM1..PM4）
     */
    @Column(nullable = false, length = 4)
    private String timeslot; // AM1..AM4, PM1..PM4

    /**
     * 挂号时间（非空，默认 NOW()），应用侧只读
     */
    @Column(name = "registration_time", insertable = false, updatable = false)
    private LocalDateTime registrationTime;

    /**
     * 挂号状态（非空），例如：PENDING / PAID / CANCELLED / COMPLETED
     */
    @Column(nullable = false, length = 20)
    private String status;
}
