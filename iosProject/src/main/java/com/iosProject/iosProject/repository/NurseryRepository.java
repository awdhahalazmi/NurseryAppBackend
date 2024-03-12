package com.iosProject.iosProject.repository;

import com.iosProject.iosProject.entity.ChildEntity;
import com.iosProject.iosProject.entity.NurseryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface NurseryRepository extends JpaRepository<NurseryEntity, Long> {

    List<NurseryEntity> findByCaseIdAndAreaId(Long caseId, Long areaId);
}
