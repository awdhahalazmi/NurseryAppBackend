package com.iosProject.iosProject.service.child;

import com.iosProject.iosProject.bo.child.Child;
import com.iosProject.iosProject.entity.CaseEntity;
import com.iosProject.iosProject.entity.ChildEntity;
import com.iosProject.iosProject.entity.UserEntity;
import com.iosProject.iosProject.repository.CaseRepository;
import com.iosProject.iosProject.repository.ChildRepository;
import com.iosProject.iosProject.repository.UserRepository;
import com.iosProject.iosProject.service.user.UserService;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;




    @Service
    public class ChildServiceImpl implements ChildService {

        private final ChildRepository childRepository;
        private final UserRepository userRepository;
        private final CaseRepository caseRepository;

        public ChildServiceImpl(ChildRepository childRepository, UserRepository userRepository, CaseRepository caseRepository) {
            this.childRepository = childRepository;
            this.userRepository = userRepository;
            this.caseRepository = caseRepository;
        }

        @Override
        public void registerChildForUser(Child child, Long userId) {
            UserEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
            CaseEntity caseEntity = caseRepository.findById(child.getCaseId())
                    .orElseThrow(() -> new IllegalArgumentException("Case not found with ID: " + userId));

            ChildEntity childEntity = new ChildEntity();
            childEntity.setName(child.getName());
            childEntity.setAge(child.getAge());
            childEntity.setUserId(user);
            childEntity.setCaseEntity(caseEntity);

            childRepository.save(childEntity);
        }

        @Override
        public Child getChildById(Long childId) {
            ChildEntity childEntity = childRepository.findById(childId)
                    .orElse(null);
            if (childEntity != null) {
                Child child = new Child();
                child.setChildId(childEntity.getId());
                child.setName(childEntity.getName());
                child.setAge(childEntity.getAge());
                child.setCaseId(childEntity.getCaseEntity().getId());

                return child;
            } else {
                return null;
            }
        }}