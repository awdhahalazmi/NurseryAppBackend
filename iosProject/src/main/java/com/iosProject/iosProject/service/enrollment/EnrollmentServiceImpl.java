package com.iosProject.iosProject.service.enrollment;

import com.iosProject.iosProject.bo.child.ChildWithNurseryId;
import com.iosProject.iosProject.bo.enrollment.Enrollment;
import com.iosProject.iosProject.entity.ChildEntity;
import com.iosProject.iosProject.entity.EnrollmentEntity;
import com.iosProject.iosProject.entity.NurseryEntity;
import com.iosProject.iosProject.entity.UserEntity;
import com.iosProject.iosProject.repository.EnrollmentRepository;
import com.iosProject.iosProject.repository.NurseryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final NurseryRepository nurseryRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, NurseryRepository nurseryRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.nurseryRepository = nurseryRepository;
    }

    @Override
    public void enrollChild(Long nurseryId, Enrollment enrollment) {
        NurseryEntity nurseryEntity = nurseryRepository.findById(nurseryId)
                .orElseThrow(() -> new IllegalArgumentException("Nursery not found with ID: " + nurseryId));

        EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
        enrollmentEntity.setNurseryId(nurseryEntity);


        ChildEntity childEntity = new ChildEntity();
        childEntity.setId(enrollment.getChildId());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(enrollment.getUserId());

        enrollmentEntity.setChildId(childEntity);
        enrollmentEntity.setUserId(userEntity);
        enrollmentEntity.setSemester(enrollment.getSemester());

        enrollmentRepository.save(enrollmentEntity);
    }

    @Override
    public List<ChildWithNurseryId> getChildAndNurseryIdByChildId(Long childId) {
        List<EnrollmentEntity> enrollments = enrollmentRepository.findByChildId_Id(childId);
        List<ChildWithNurseryId> result = new ArrayList<>();

        for (EnrollmentEntity enrollment : enrollments) {
            ChildWithNurseryId childWithId = new ChildWithNurseryId();
            childWithId.setChildId(enrollment.getChildId().getId());
            childWithId.setChildName(enrollment.getChildId().getName());
            childWithId.setNurseryId(enrollment.getNurseryId().getId());
            result.add(childWithId);
        }

        return result;
    }
}
