package com.iosProject.iosProject.repository;

import com.iosProject.iosProject.entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AreaRepository extends JpaRepository<AreaEntity, Long>  {


}
