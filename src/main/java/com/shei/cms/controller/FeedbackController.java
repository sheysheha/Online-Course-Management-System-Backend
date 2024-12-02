package com.shei.cms.controller;


import com.shei.cms.entity.Course;
import com.shei.cms.entity.Feedback;
import com.shei.cms.entity.User;
import com.shei.cms.repository.FeedbackRepository;
import com.shei.cms.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackRepository feedbackRepository;


    @PostMapping
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return feedbackRepository.save(feedback);
    }
    @GetMapping("/all")
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
    @GetMapping("/{courseId}")
    public ResponseEntity<List<Feedback>> getFeedbacksByCourse(@PathVariable int courseId) {
        // Query the feedbacks by courseId
        List<Feedback> feedbacks = feedbackRepository.findByCourseId(courseId);

        if (feedbacks.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if no feedbacks are found
        }

        return ResponseEntity.ok(feedbacks); // Return feedbacks with status 200
    }
}

