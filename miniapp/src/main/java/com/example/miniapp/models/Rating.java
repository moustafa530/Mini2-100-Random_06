package com.example.miniapp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Rating {
    @Id
    private String ID;
    private Long entityId;
    private String entityType;
    private Integer score;
    private String comment;
    private LocalDateTime ratingDate;

    public Rating() {

    }

    public Rating(String ID, Long entityId, String entityType, Integer score, String comment, LocalDateTime ratingDate) {
        this.ID = ID;
        this.entityId = entityId;
        this.entityType = entityType;
        this.score = score;
        this.comment = comment;
        this.ratingDate = ratingDate;

    }

    public Rating(Long entityId, String entityType, Integer score, String comment) {

        this.entityId = entityId;
        this.entityType = entityType;
        this.score = score;
        this.comment = comment;


    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(LocalDateTime ratingDate) {
        this.ratingDate = ratingDate;
    }
}
