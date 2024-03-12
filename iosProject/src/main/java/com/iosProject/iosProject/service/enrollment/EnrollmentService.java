package com.iosProject.iosProject.service.enrollment;

import com.iosProject.iosProject.bo.child.ChildWithNurseryId;
import com.iosProject.iosProject.bo.enrollment.Enrollment;
import com.iosProject.iosProject.entity.EnrollmentEntity;

import java.util.List;

public interface EnrollmentService {
    void enrollChild(Long nurseryId, Enrollment enrollment);
    List<ChildWithNurseryId> getChildAndNurseryIdByChildId(Long childId);


}
