package com.iosProject.iosProject.entity;


import javax.persistence.*;

@Entity
@Table(name = "enrollment")
public class EnrollmentEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "semester", nullable = false)
    private String semester;

    @OneToOne
    @JoinColumn(name = "child_id")
    private ChildEntity childId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

     @ManyToOne
    @JoinColumn(name = "nursery_id")
    private NurseryEntity nurseryId;
    public NurseryEntity getNurseryId() {
        return nurseryId;
    }

    public void setNurseryId(NurseryEntity nurseryId) {
        this.nurseryId = nurseryId;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public ChildEntity getChildId() {
        return childId;
    }

    public void setChildId(ChildEntity childId) {
        this.childId = childId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //in the erd we added one to one but i think it should be many enrollment
    // belong to one nursery
}

