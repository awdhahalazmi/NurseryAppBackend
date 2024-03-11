package com.iosProject.iosProject.entity;

import javax.persistence.*;

@Entity
@Table(name = "child_case")
public class CaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

//    @ManyToOne
//    @JoinColumn(name = "child_id")
//    private ChildEntity childId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public ChildEntity getChildId() {
//        return childId;
//    }
//
//    public void setChildId(ChildEntity childId) {
//        this.childId = childId;
//    }
}
