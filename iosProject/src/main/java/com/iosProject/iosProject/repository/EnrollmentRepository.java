package com.iosProject.iosProject.repository;

import com.iosProject.iosProject.entity.EnrollmentEntity;
import com.iosProject.iosProject.entity.NurseryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {
    List<EnrollmentEntity> findByChildId_Id(Long childId);
}
