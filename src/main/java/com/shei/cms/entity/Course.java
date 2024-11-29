package com.shei.cms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    private int instructorId;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseMaterial> materials;
}
