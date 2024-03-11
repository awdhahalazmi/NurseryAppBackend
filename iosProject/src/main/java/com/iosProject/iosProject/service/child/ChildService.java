package com.iosProject.iosProject.service.child;

import com.iosProject.iosProject.bo.child.Child;

public interface ChildService {

    void registerChildForUser(Child child, Long userId);
    Child getChildById(Long childId);

}
