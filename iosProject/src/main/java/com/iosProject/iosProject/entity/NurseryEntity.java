package com.iosProject.iosProject.entity;


import javax.persistence.*;

@Entity
@Table(name = "nursery")
public class NurseryEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "image", nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private CaseEntity caseId;


    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaEntity areaId;

    @OneToOne
    @JoinColumn(name = "enrollment_id")
    private EnrollmentEntity enrollmentId;


    public CaseEntity getCaseId() {
        return caseId;
    }

    public void setCaseId(CaseEntity caseId) {
        this.caseId = caseId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AreaEntity getAreaId() {
        return areaId;
    }

    public void setAreaId(AreaEntity areaId) {
        this.areaId = areaId;
    }
}
