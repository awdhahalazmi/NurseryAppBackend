package com.iosProject.iosProject.service.childCase;

import com.iosProject.iosProject.entity.AreaEntity;
import com.iosProject.iosProject.entity.CaseEntity;

import java.util.List;

public interface CaseService {
    List<CaseEntity> getAllCases();
    CaseEntity addCase(CaseEntity childCase);
}
