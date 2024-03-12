package com.iosProject.iosProject.service.area;

import com.iosProject.iosProject.entity.AreaEntity;

import java.util.List;

public interface AreaService {
    List<AreaEntity> getAllAreas();
    AreaEntity addArea(AreaEntity area);

}
