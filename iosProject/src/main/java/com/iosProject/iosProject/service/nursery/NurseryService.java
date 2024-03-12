package com.iosProject.iosProject.service.nursery;

import com.iosProject.iosProject.bo.nursery.Nursery;
import com.iosProject.iosProject.entity.NurseryEntity;

import java.util.List;

public interface NurseryService {
    void registerNursery(Nursery nursery);
    List<NurseryEntity> getNurseriesByCaseAndArea( Long caseId, Long areaId);

}
