package com.shei.cms.repository;
import com.shei.cms.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByCourseId(int courseId);
}
