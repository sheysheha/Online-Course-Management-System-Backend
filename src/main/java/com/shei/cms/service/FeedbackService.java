package com.shei.cms.service;

import com.shei.cms.entity.Feedback;
import com.shei.cms.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbacksByCourseId(int courseId) {
        return feedbackRepository.findByCourseId(courseId);
    }
}

