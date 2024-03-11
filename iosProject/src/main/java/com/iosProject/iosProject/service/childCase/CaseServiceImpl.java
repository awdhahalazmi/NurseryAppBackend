package com.iosProject.iosProject.service.childCase;

import com.iosProject.iosProject.entity.CaseEntity;
import com.iosProject.iosProject.repository.AreaRepository;
import com.iosProject.iosProject.repository.CaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseServiceImpl implements CaseService{

    private final CaseRepository caseRepository;

    public CaseServiceImpl(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Override
    public List<CaseEntity> getAllCases() {
        return caseRepository.findAll();    }

    @Override
    public CaseEntity addCase(CaseEntity childCase) {
        if (childCase.getName() == null || childCase.getName().isEmpty()) {
            throw new IllegalArgumentException("Area name cannot be empty");
        }


        return caseRepository.save(childCase);
    }
}
