package com.iosProject.iosProject.service.nursery;

import com.iosProject.iosProject.bo.nursery.Nursery;
import com.iosProject.iosProject.entity.AreaEntity;
import com.iosProject.iosProject.entity.CaseEntity;
import com.iosProject.iosProject.entity.NurseryEntity;
import com.iosProject.iosProject.repository.AreaRepository;
import com.iosProject.iosProject.repository.CaseRepository;
import com.iosProject.iosProject.repository.NurseryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class NurseryServiceImp implements NurseryService{


    private final NurseryRepository nurseryRepository;
    private final CaseRepository caseRepository;
    private final AreaRepository areaRepository;

    public NurseryServiceImp(NurseryRepository nurseryRepository, CaseRepository caseRepository, AreaRepository areaRepository) {
        this.nurseryRepository = nurseryRepository;
        this.caseRepository = caseRepository;
        this.areaRepository = areaRepository;
    }


        @Override
        public void registerNursery(Nursery nursery) {

            CaseEntity caseEntity = caseRepository.findById(nursery.getCaseId())
                    .orElseThrow(() -> new IllegalArgumentException("Case not found with ID: "));
            AreaEntity areaEntity = areaRepository.findById(nursery.getAreaId())
                    .orElseThrow(() -> new IllegalArgumentException("Area not found with ID: "));

            NurseryEntity nurseryEntity = new NurseryEntity();
            nurseryEntity.setName(nursery.getName());
            nurseryEntity.setLocation(nursery.getLocation());
            nurseryEntity.setDetails(nursery.getDetail());
            nurseryEntity.setImageUrl(nursery.getImageUrl());
            nurseryEntity.setCaseId(caseEntity);
            nurseryEntity.setAreaId(areaEntity);

            nurseryRepository.save(nurseryEntity);
        }


    @Override
    public List<NurseryEntity> getNurseriesByCaseAndArea( Long caseId, Long areaId) {
        return nurseryRepository.findAll()
                .stream()
                .filter(nursery -> caseId == null || Objects.equals(nursery.getCaseId().getId(), caseId))
                .filter(nursery -> areaId == null || Objects.equals(nursery.getAreaId().getId(), areaId))
                .collect(Collectors.toList());
    }


}
