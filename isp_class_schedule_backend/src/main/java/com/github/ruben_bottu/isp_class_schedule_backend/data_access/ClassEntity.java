package com.github.ruben_bottu.isp_class_schedule_backend.data_access;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "class")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "start.timestamp.missing")
    @Column(name = "start_timestamp")
    private LocalDateTime startTimestamp;

    @NotNull(message = "end.timestamp.missing")
    @Column(name = "end_timestamp")
    private LocalDateTime endTimestamp;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_group_id", referencedColumnName = "id") // Should not be necessary
    private CourseGroupEntity courseGroup;

    public String getCourseName() {
        return (courseGroup == null) ? "" : courseGroup.getCourseName();
    }

    public String getGroupName() {
        return (courseGroup == null) ? "" : courseGroup.getGroupName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClassEntity classEntity = (ClassEntity) o;
        return id != null && Objects.equals(id, classEntity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    // ######################
    //      Generated       #
    // ######################

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(LocalDateTime startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public LocalDateTime getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(LocalDateTime endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public CourseGroupEntity getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(CourseGroupEntity courseGroup) {
        this.courseGroup = courseGroup;
    }

    @Override
    public String toString() {
        return "ClassEntity{" +
                "id=" + id +
                ", startTimestamp=" + startTimestamp +
                ", endTimestamp=" + endTimestamp +
                ", courseName=" + getCourseName() +
                ", groupName=" + getGroupName() +
                '}';
    }
}