package com.shei.cms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CourseMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;
    private String fileName;
    private String fileUrl;
}
