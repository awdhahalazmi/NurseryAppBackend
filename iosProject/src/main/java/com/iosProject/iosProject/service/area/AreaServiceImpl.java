package com.iosProject.iosProject.service.area;


import com.iosProject.iosProject.entity.AreaEntity;
import com.iosProject.iosProject.repository.AreaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService{

    private final AreaRepository areaRepository;

    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public List<AreaEntity> getAllAreas() {
        return areaRepository.findAll();
    }


    @Override
    public AreaEntity addArea(AreaEntity area) {
        if (area.getName() == null || area.getName().isEmpty()) {
            throw new IllegalArgumentException("Area name cannot be empty");
        }


        return areaRepository.save(area);
    }
    }



